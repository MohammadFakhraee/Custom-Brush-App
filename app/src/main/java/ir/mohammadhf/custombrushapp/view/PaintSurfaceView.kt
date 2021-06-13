package ir.mohammadhf.custombrushapp.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import ir.mohammadhf.custombrushapp.utils.PaintManager
import ir.mohammadhf.custombrushapp.utils.PaintThread
import ir.mohammadhf.custombrushapp.holder.SurfaceViewHolder

class PaintSurfaceView @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet?,
    defStyleAttr: Int = -1
) : SurfaceView(context, attributeSet, defStyleAttr),
    SurfaceHolder.Callback {

    init {
        holder.addCallback(this)
    }

//    var paintManager = PaintManager(
////        RandDegBitmapBrush(BitmapFactory.decodeResource(resources, R.drawable.img_brush_1))
//        QuadMarkerBrush()
//    )

    lateinit var paintManager: PaintManager

    private lateinit var paintThread: PaintThread

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
        Log.i("draw", "$width and $height")
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // do something if you want
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        paintThread = PaintThread(
            SurfaceViewHolder(
                holder!!
            ), paintManager
        )
        paintThread.isRunning = true
        paintThread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
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
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, 3 * measuredHeight / 4)
    }
}