package ir.mohammadhf.custombrushapp.view

import android.content.Context
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.TextureView
import ir.mohammadhf.custombrushapp.utils.PaintManager
import ir.mohammadhf.custombrushapp.utils.PaintThread
import ir.mohammadhf.custombrushapp.holder.TextureViewHolder
import ir.mohammadhf.custombrushapp.brushes.spray.SquareSprayBrush

class PaintTextureView @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet?,
    defAttrStyle: Int = 0
) : TextureView(context, attributeSet, defAttrStyle),
    TextureView.SurfaceTextureListener {

    init {
        surfaceTextureListener = this
    }

    var paintManager = PaintManager(
        //                RandDegBitmapBrush(BitmapFactory.decodeResource(resources, R.drawable.img_brush_1))
        SquareSprayBrush()
    )
    lateinit var paintThread: PaintThread

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paintManager.onTouchDown(x, y)
                invalidate()
                true
            }
            MotionEvent.ACTION_MOVE -> {
                paintManager.onTouchMove(x, y)
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                paintManager.onTouchUp(x, y)
                invalidate()
                true
            }
            else -> false
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paintManager.onSizeChange(width, height)
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        // do something
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        // do something
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        paintThread = PaintThread(
            TextureViewHolder(
                this
            ), paintManager
        )
        paintThread.isRunning = true
        paintThread.start()
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        var retry = true

        paintThread.isRunning = false

        while (retry) {
            try {
                paintThread.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        return false
    }
}