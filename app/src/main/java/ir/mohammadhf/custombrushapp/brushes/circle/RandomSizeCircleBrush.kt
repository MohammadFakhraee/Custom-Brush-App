package ir.mohammadhf.custombrushapp.brushes.circle

import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import kotlin.random.Random

class RandomSizeCircleBrush : StampBrush() {
    init {
        paint.color = Color.RED
    }
    private val random = Random(1)
    private val randRadius
        get() = (random.nextInt(1, 5) * 10).toFloat()
    private val randAlpha
        get() = random.nextInt(30, 200)

    private var radius = 0f
    private var alpha = 0

    override fun onTouchMove(x: Float, y: Float) {
        super.onTouchMove(x, y)
        setRadiusAndAlpha()
    }

    override fun onTouchUp(x: Float, y: Float) {
        super.onTouchUp(x, y)
        setRadiusAndAlpha()
    }

    override fun drawOn(paintCanvas: Canvas) {
        for(point in points) {
            paint.alpha = randAlpha
            paintCanvas.drawCircle(point.x, point.y, randRadius, paint)
        }
    }

    private fun setRadiusAndAlpha() {
        radius = randRadius
        alpha = randAlpha
    }
}