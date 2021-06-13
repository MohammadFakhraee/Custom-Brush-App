package ir.mohammadhf.custombrushapp.brushes.connect

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import android.util.Log
import ir.mohammadhf.custombrushapp.brushes.Brush
import kotlin.math.pow

class NearPointsBrush : Brush() {

    init {
        paint.color = Color.BLACK
    }

    private val resetTolerance = 40
    private val pathOffset = 0.25f

    private val mainPathStroke = 6f
    private val sidePathStroke = 4f

    private val mainPath = Path()
    private val sidePath = Path()

    private val points = ArrayList<PointF>()
    private var resetCounter = 1

    override fun onTouchDown(x: Float, y: Float) {
        points.add(PointF(x, y))
        mainPath.moveTo(x, y)
    }

    override fun onTouchMove(x: Float, y: Float) {
        val prevPoint = points[points.size - 1]

        mainPath.quadTo(
            prevPoint.x, prevPoint.y,
            (prevPoint.x + x) / 2, (prevPoint.y + y) / 2
        )

        if (resetCounter >= resetTolerance) {
            shouldReset = true
            backCounterToDefault()
        }

        for (point in points) {
            val dx = x - point.x
            val dy = y - point.y

            if (dx.pow(2) + dy.pow(2) < 10000) {
                sidePath.moveTo(point.x + (pathOffset * dx), point.y + (pathOffset * dy))
                sidePath.lineTo(x - (pathOffset * dx), y - (pathOffset * dy))
            }
        }

        if (shouldReset) points.add(
            PointF(
                (x + prevPoint.x) / 2, (y + prevPoint.y) / 2
            )
        )
        else points.add(PointF(x, y))
        resetCounter++
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        backCounterToDefault()
        points.clear()
    }

    override fun drawOn(paintCanvas: Canvas) {
        paint.alpha = 255
        paint.strokeWidth = mainPathStroke
        paintCanvas.drawPath(mainPath, paint)
        paint.alpha = 75
        paint.strokeWidth = sidePathStroke
        paintCanvas.drawPath(sidePath, paint)
    }

    override fun resetBrush() {
        mainPath.reset()
        val lastP = points.last()
        mainPath.moveTo(lastP.x, lastP.y)
        sidePath.reset()
    }

    private fun backCounterToDefault() {
        resetCounter = 1
    }
}