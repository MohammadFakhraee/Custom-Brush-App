package ir.mohammadhf.custombrushapp.brushes

import android.graphics.Color
import android.graphics.Paint
import ir.mohammadhf.custombrushapp.utils.ITouchListener
abstract class Brush : ITouchListener {
    companion object {
        const val DEF_STROKE_SIZE: Float = 40f
    }

    val paint: Paint = Paint()

    protected var shouldReset = false

    init {
        paint.apply {
            isAntiAlias = true
            isDither = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = DEF_STROKE_SIZE
        }
    }

    open fun changeColor(newColor: Int) {
        paint.color = newColor
    }

    open fun shouldReset(): Boolean = shouldReset

    abstract fun resetBrush()

    fun reset() {
        shouldReset = false
        resetBrush()
    }
}

