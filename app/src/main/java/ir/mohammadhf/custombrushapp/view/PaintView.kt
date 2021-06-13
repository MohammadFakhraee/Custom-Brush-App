package ir.mohammadhf.custombrushapp.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import ir.mohammadhf.custombrushapp.utils.PaintManager
import ir.mohammadhf.custombrushapp.R
import ir.mohammadhf.custombrushapp.brushes.bitmap.RandDegBitmapBrush

class PaintView @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet?,
    defStyleAttr: Int = -1
) : View(context, attributeSet, defStyleAttr) {

    var paintManager = PaintManager(
        RandDegBitmapBrush(BitmapFactory.decodeResource(resources, R.drawable.img_brush_1))
//        SquareSprayBrush()
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintManager.drawOn(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paintManager.onSizeChange(width, height)
    }

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
}