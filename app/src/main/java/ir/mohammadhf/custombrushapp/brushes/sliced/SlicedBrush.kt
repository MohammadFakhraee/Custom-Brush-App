package ir.mohammadhf.custombrushapp.brushes.sliced

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush

open class SlicedBrush : Brush() {
    companion object {
        const val MAX_LINES = 5
    }

    init {
        paint.strokeWidth = 15f
        paint.strokeCap = Paint.Cap.SQUARE
    }

    private val path = Path()
    private val lastP = PointF()
    open val lineOffset
        get() = 2 * paint.strokeWidth / 5

    override fun onTouchDown(x: Float, y: Float) {
        lastP.x = x
        lastP.y = y
    }

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true
        for (index in 0 until MAX_LINES) {
            makeLine(lastP.x, lastP.y, x, y) {
                -(2 - index) * lineOffset
            }
        }

        lastP.set(x, y)
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        for (index in 0 until MAX_LINES) {
            makeLine(x, y, x, y) {
                -(2 - index) * lineOffset
            }
        }
    }

    override fun drawOn(paintCanvas: Canvas) {
        paintCanvas.drawPath(path, paint)
    }

    private fun makeLine(
        firstPointX: Float,
        firstPointY: Float,
        secondPointX: Float,
        secondPointY: Float,
        offset: () -> Float
    ) {
        val offsetPixel = offset()
        path.moveTo(firstPointX + offsetPixel, firstPointY + offsetPixel)
        path.lineTo(secondPointX + offsetPixel, secondPointY + offsetPixel)
    }

    override fun resetBrush() {
        path.reset()
    }
}