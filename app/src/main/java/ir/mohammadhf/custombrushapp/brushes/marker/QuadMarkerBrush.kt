package ir.mohammadhf.custombrushapp.brushes.marker

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush

open class QuadMarkerBrush : Brush() {
    open val resetTolerance = 20

    private val lastPoint = PointF()
    val path = Path()

    var isQuad = true

    private var countToReset = 1

    override fun onTouchDown(x: Float, y: Float) {
        lastPoint.set(x, y)
        path.moveTo(x, y)
        path.lineTo(x, y)
    }

    override fun onTouchMove(x: Float, y: Float) {
        makeLine(x, y)
        if (countToReset >= resetTolerance) {
            backCountToDefault()
            shouldReset = true
        }
        if (shouldReset) lastPoint.set((x + lastPoint.x) / 2, (y + lastPoint.y) / 2)
        else lastPoint.set(x, y)
        countToReset++
    }

    override fun onTouchUp(x: Float, y: Float) {
        backCountToDefault()
        shouldReset = true
    }

    override fun drawOn(paintCanvas: Canvas) {
        paintCanvas.drawPath(path, paint)
    }

    private fun makeLine(x: Float, y: Float) {
        if (isQuad)
            path.quadTo(
                lastPoint.x, lastPoint.y,
                (x + lastPoint.x) / 2, (y + lastPoint.y) / 2
            )
        else {
            path.moveTo(lastPoint.x, lastPoint.y)
            path.lineTo(x, y)
        }
    }

    override fun resetBrush() {
        path.reset()
        path.moveTo(lastPoint.x, lastPoint.y)
    }

    private fun backCountToDefault() {
        countToReset = 1
    }
}