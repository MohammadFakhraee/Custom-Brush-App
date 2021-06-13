package ir.mohammadhf.custombrushapp.brushes.connect

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush

class AllPointsBrush : Brush() {
    init {
        paint.strokeWidth = 4f
        paint.color = Color.BLACK
    }

    val maxPointOffset = 4

    val points = ArrayList<PointF>()
    val path = Path()

    private var isDrawing = false

    override fun onTouchDown(x: Float, y: Float) {
        isDrawing = true
        points.add(PointF(x, y))
    }

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true

        points.add(PointF(x, y))

        path.moveTo(points[0].x, points[0].y)

        for ((index, point) in points.withIndex()) {
            path.lineTo(point.x, point.y)

            if (index - maxPointOffset >= 0) {
                val newIndex = index - maxPointOffset
                path.moveTo(points[newIndex].x, points[newIndex].y)
                path.lineTo(point.x, point.y)
            }
        }
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        isDrawing = false
    }

    override fun drawOn(paintCanvas: Canvas) {
        paintCanvas.drawPath(path, paint)
    }

    override fun resetBrush() {
        if (isDrawing) points.removeAt(0)
        else points.clear()
        path.reset()
    }

    override fun shouldReset(): Boolean =
        if (isDrawing) shouldReset && points.size > maxPointOffset
        else shouldReset


}