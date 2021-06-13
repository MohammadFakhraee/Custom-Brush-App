package ir.mohammadhf.custombrushapp.brushes.marker

import android.graphics.*
import ir.mohammadhf.custombrushapp.brushes.Brush
import kotlin.math.*

open class LineCircleGradientBrush : Brush() {
    companion object {
        const val CIRCLE_RADIUS = 20f
        const val DISTANCE_THRESHOLD = 10
    }

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    private val points = ArrayList<PointF>()

    override fun onTouchDown(x: Float, y: Float) {
        points.add(PointF(x, y))
    }

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true

        val currentPoint = PointF(x, y)
        points.add(currentPoint)
        val currentPointIndex = points.size - 1
        val lastPoint = points[currentPointIndex - 1]

        val distance = getDistance(lastPoint, currentPoint)
        val angle = getAngle(lastPoint, currentPoint)

        val distanceThreshold = getDistanceThreshold()

        if (distance >= distanceThreshold) {
            for (i in distanceThreshold..distance.toInt() step distanceThreshold) {
                val pointF = PointF()
                pointF.x = lastPoint.x + (cos(angle) * i)
                pointF.y = lastPoint.y + (sin(angle) * i)
                addPointAt(currentPointIndex - 1, pointF)
            }
        }
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        points.add(PointF(x, y))
    }

    override fun drawOn(paintCanvas: Canvas) {
        for (point in points) {
            paint.shader = RadialGradient(
                point.x, point.y,
                CIRCLE_RADIUS,
                paint.color, Color.TRANSPARENT, Shader.TileMode.CLAMP
            )
            paintCanvas.drawCircle(point.x, point.y,
                CIRCLE_RADIUS, paint)
        }
    }

    open fun addPointAt(index: Int, pointF: PointF) {
        points.add(index, pointF)
    }

    private fun getDistance(firstPoint: PointF, secondPoint: PointF): Float =
        sqrt(
            (secondPoint.x - firstPoint.x).pow(2) +
                    (secondPoint.y - firstPoint.y).pow(2)
        )

    private fun getAngle(firstPoint: PointF, secondPoint: PointF): Float =
        atan2(secondPoint.y - firstPoint.y, secondPoint.x - firstPoint.x)

    open fun getDistanceThreshold(): Int =
        DISTANCE_THRESHOLD

    override fun resetBrush() {
        val lastPoint = points.last()
        points.clear()
        points.add(lastPoint)
    }
}

