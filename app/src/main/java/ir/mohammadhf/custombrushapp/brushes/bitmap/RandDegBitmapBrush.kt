package ir.mohammadhf.custombrushapp.brushes.bitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.PointF
import kotlin.random.Random

class RandDegBitmapBrush(bitmap: Bitmap): BitmapBrush(bitmap) {
    companion object {
        const val DISTANCE_THRESHOLD = 24
    }
    private val matrix = Matrix()

    private val random = Random(1)
    private val randDegree: Float
        get() = random.nextFloat() * 361

    private val degrees = ArrayList<Float>()

    override fun onTouchDown(x: Float, y: Float) {
        super.onTouchDown(x, y)
        degrees.add(randDegree)
    }

    override fun onTouchMove(x: Float, y: Float) {
        super.onTouchMove(x, y)
        degrees.add(randDegree)
    }

    override fun drawAtPoint(canvas: Canvas, pointF: PointF, index: Int) {
        matrix.reset()
        matrix.postTranslate((-bitmapWidth / 2).toFloat(), (-bitmapHeight / 2).toFloat())
        matrix.postRotate(degrees[index])
        matrix.postTranslate(pointF.x, pointF.y)
        canvas.drawBitmap(
            pattern, matrix, paint
        )
    }

    override fun addPointAt(index: Int, pointF: PointF) {
        super.addPointAt(index, pointF)
        degrees.add(index, randDegree)
    }

    override fun resetBrush() {
        super.resetBrush()

        if (isDrawing) {
            val value = degrees.last()
            degrees.clear()
            degrees.add(value)
        } else degrees.clear()
    }

    override fun getDistanceThreshold(): Int =
        DISTANCE_THRESHOLD
}