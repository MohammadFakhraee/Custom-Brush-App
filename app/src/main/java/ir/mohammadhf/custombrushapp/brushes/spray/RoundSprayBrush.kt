package ir.mohammadhf.custombrushapp.brushes.spray

import android.graphics.Canvas
import android.graphics.PointF
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class RoundSprayBrush : SquareSprayBrush() {

    override fun addPointsAroundPoint(x: Float, y: Float) {
        for (index in 0 until density) {
            val randAngel = random.nextInt(0, 359).toFloat()
            val randRadius = random.nextInt(1 * radius.toInt() / 12, radius.toInt())
            pixels.add(
                PointF(
                    x + cos(randAngel) * randRadius,
                    y + sin(randAngel) * randRadius
                )
            )
        }
    }
}