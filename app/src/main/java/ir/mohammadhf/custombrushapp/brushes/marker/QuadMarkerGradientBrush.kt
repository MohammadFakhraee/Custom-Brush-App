package ir.mohammadhf.custombrushapp.brushes.marker

import android.graphics.Canvas
import kotlin.math.ceil

class QuadMarkerGradientBrush : QuadMarkerBrush() {
    override val resetTolerance = 10

    private val paintStroke = paint.strokeWidth
    private val opacityStep = ceil(paintStroke / 16).toInt()

    private var opacityStepCounter = 0

    override fun drawOn(paintCanvas: Canvas) {
        for (i in 0..paintStroke.toInt() step opacityStep) {
            opacityStepCounter = i
            paint.strokeWidth = i.toFloat()
            paint.alpha = getOpacity(i)
            paintCanvas.drawPath(path, paint)
        }
    }

    private fun getOpacity(index: Int): Int =
        (255 - (index / paintStroke) * 200).toInt()

    override fun shouldReset(): Boolean =
        shouldReset && (paintStroke.toInt() - opacityStepCounter) < opacityStep
}