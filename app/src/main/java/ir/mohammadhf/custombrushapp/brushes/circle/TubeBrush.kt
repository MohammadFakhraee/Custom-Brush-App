package ir.mohammadhf.custombrushapp.brushes.circle

import android.graphics.PointF
import android.util.Log
import kotlin.math.*

class TubeBrush : StampBrush() {
    companion object {
        const val DISTANCE_THRESHOLD = 15
    }

    private var isDrawing = false

    override fun onTouchDown(x: Float, y: Float) {
        points.add(PointF(x, y))
        isDrawing = true
    }

    override fun onTouchMove(x: Float, y: Float) {
        super.onTouchMove(x, y)

        val currentPoint = points[points.size - 1]
        val lastPoint = points[points.size - 2]

        val distance = getDistance(lastPoint, currentPoint)
        val angle = getAngle(lastPoint, currentPoint)

        if (distance >= DISTANCE_THRESHOLD) {
            for (i in DISTANCE_THRESHOLD..distance.toInt() step DISTANCE_THRESHOLD) {
                val pointF = PointF()
                pointF.x = lastPoint.x + (cos(angle) * i)
                pointF.y = lastPoint.y + (sin(angle) * i)
                points.add(points.size - 1, pointF)
            }
        }
    }

    override fun onTouchUp(x: Float, y: Float) {
        super.onTouchUp(x, y)
        isDrawing = false
    }

    override fun resetBrush() {
        if (isDrawing) {
            val lastPoint = points.last()
            points.clear()
            points.add(lastPoint)
        } else points.clear()
    }

    private fun getDistance(firstPoint: PointF, secondPoint: PointF): Float =
        sqrt(
            (secondPoint.x - firstPoint.x).pow(2) +
                    (secondPoint.y - firstPoint.y).pow(2)
        )

    private fun getAngle(firstPoint: PointF, secondPoint: PointF): Float =
        atan2(secondPoint.y - firstPoint.y, secondPoint.x - firstPoint.x)
}