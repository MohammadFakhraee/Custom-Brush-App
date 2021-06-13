package ir.mohammadhf.custombrushapp.brushes.spray

import android.graphics.Canvas
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush
import kotlin.math.pow
import kotlin.random.Random

open class SquareSprayBrush : Brush() {
    init {
        paint.strokeWidth = 3f
    }

    var radius = 100f
    var density: Int = 40

    val pixels = ArrayList<PointF>()

    val random = Random(1)

    private val randomNumber
        get() = random.nextInt((-radius).toInt(), (+radius).toInt()).toFloat()

    private val randomStroke
        get() = (random.nextInt(1, radius.toInt() / 4) * 2).toFloat()

    private val randomAlpha
        get() = random.nextInt(3, 7) * 25

    override fun onTouchDown(x: Float, y: Float) {
        shouldReset = true
        addPointsAroundPoint(x, y)
    }

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true
        addPointsAroundPoint(x, y)
    }

    override fun onTouchUp(x: Float, y: Float) {

    }

    override fun drawOn(paintCanvas: Canvas) {
        for (pixel in pixels) {
            paint.strokeWidth = randomStroke
            paint.alpha = randomAlpha
            paintCanvas.drawPoint(
                pixel.x, pixel.y, paint
            )
        }
    }

    open fun addPointsAroundPoint(x: Float, y: Float) {
        for (index in 0 until density)
            pixels.add(
                PointF(
                    randomNumberPlusVar(x),
                    randomNumberPlusVar(y)
                )
            )
    }

    open fun randomNumberPlusVar(variable: Float): Float =
        variable + randomNumber

    override fun resetBrush() {
        pixels.clear()
    }
}