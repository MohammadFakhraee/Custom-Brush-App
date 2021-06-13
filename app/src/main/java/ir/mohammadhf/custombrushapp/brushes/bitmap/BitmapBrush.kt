package ir.mohammadhf.custombrushapp.brushes.bitmap

import android.graphics.*
import ir.mohammadhf.custombrushapp.brushes.Brush
import kotlin.math.*

open class BitmapBrush(var pattern: Bitmap) : Brush() {
    companion object {
        const val DISTANCE_THRESHOLD = 8
    }

    init {
        pattern = pattern.copy(Bitmap.Config.ARGB_8888, true)
    }

    val bitmapWidth
        get() = pattern.width
    val bitmapHeight
        get() = pattern.height

    private val points = ArrayList<PointF>()

    protected var isDrawing = false

    override fun onTouchDown(x: Float, y: Float) {
        points.add(PointF(x, y))
        isDrawing = true
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
        isDrawing = false
        shouldReset = true
    }

    override fun drawOn(paintCanvas: Canvas) {
        for ((index, point) in points.withIndex())
            drawAtPoint(paintCanvas, point, index)
    }

    open fun drawAtPoint(canvas: Canvas, pointF: PointF, index: Int) =
        canvas.drawBitmap(
            pattern,
            pointF.x - bitmapWidth / 2,
            pointF.y - bitmapHeight / 2,
            paint
        )

    override fun resetBrush() {
        if (isDrawing) {
            val lastPoint = points.last()
            points.clear()
            points.add(lastPoint)
        } else points.clear()
    }

    override fun changeColor(newColor: Int) {
        super.changeColor(newColor)

        val dstHSV = FloatArray(3)
        for (row in 0 until bitmapHeight) {
            for (col in 0 until bitmapWidth) {
                val alpha = Color.alpha(pattern.getPixel(col, row))

                Color.colorToHSV(newColor, dstHSV)

                pattern.setPixel(col, row, Color.HSVToColor(alpha, dstHSV))
            }
        }
    }

    open fun addPointAt(index: Int, pointF: PointF) {
        if (index < points.size)
            points.add(index, pointF)
    }

    private fun getDistance(firstPoint: PointF, secondPoint: PointF): Float =
        sqrt(
            (secondPoint.x - firstPoint.x).pow(2) +
                    (secondPoint.y - firstPoint.y).pow(2)
        )

    private fun getAngle(firstPoint: PointF, secondPoint: PointF): Float =
        atan2(secondPoint.y - firstPoint.y, secondPoint.x - firstPoint.x)

    open fun getDistanceThreshold() =
        DISTANCE_THRESHOLD
}