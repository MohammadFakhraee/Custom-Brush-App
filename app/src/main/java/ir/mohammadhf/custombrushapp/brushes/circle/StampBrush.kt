package ir.mohammadhf.custombrushapp.brushes.circle

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush

open class StampBrush : Brush() {
    companion object {
        const val CIRCLE_STROKE_WIDTH = 2f
        const val CIRCLE_RADIUS = 30f
    }

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    val points = ArrayList<PointF>()
    val strokeColor = Color.BLACK

    private var currentColor = paint.color

    override fun onTouchDown(x: Float, y: Float) {
    }

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true
        points.add(PointF(x, y))
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        points.add(PointF(x, y))
    }

    override fun drawOn(paintCanvas: Canvas) {
        for (point in points) {
            paint.color = strokeColor
            paintCanvas.drawCircle(point.x, point.y, CIRCLE_RADIUS, paint)
            paint.color = currentColor
            paintCanvas.drawCircle(point.x, point.y, CIRCLE_RADIUS - CIRCLE_STROKE_WIDTH, paint)
        }
    }

    override fun changeColor(newColor: Int) {
        super.changeColor(newColor)
        currentColor = newColor
    }

    override fun resetBrush() {
        points.clear()
    }
}