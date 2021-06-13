package ir.mohammadhf.custombrushapp.brushes.sliced

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush

open class AlphaSlicedBrush : Brush() {
    open val resetTolerance = 20

    init {
        paint.strokeWidth = 18f
    }

    private val alphaList = listOf(1f, 0.6f, 0.4f, 0.2f, 0.15f)
    private val paths = listOf(Path(), Path(), Path(), Path(), Path())
    private val lastP = PointF()

    open val lineOffset: Float
        get() = 2 * paint.strokeWidth / 5

    private var countToReset = 1

    override fun onTouchDown(x: Float, y: Float) {
        lastP.set(x, y)
        for ((index, path) in paths.withIndex()) {
            movePath(path, x, y) {
                offsetFromPoint(index)
            }
        }
    }

    override fun onTouchMove(x: Float, y: Float) {
        if (countToReset >= resetTolerance) {
            shouldReset = true
            backCountToDefault()
        }
        for ((index, path) in paths.withIndex()) {
            quadPath(path, lastP.x, lastP.y, x, y) {
                offsetFromPoint(index)
            }
        }
        if (shouldReset) lastP.set((x + lastP.x) / 2, (y + lastP.y) / 2)
        else lastP.set(x, y)

        countToReset++
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        backCountToDefault()
    }

    override fun drawOn(paintCanvas: Canvas) {
        for ((index, path) in paths.withIndex()) {
            paint.alpha = alphaNum(alphaList[index])
            paintCanvas.drawPath(path, paint)
        }
    }

    override fun resetBrush() {
        for ((index, path) in paths.withIndex()) {
            path.reset()
            path.moveTo(
                lastP.x + offsetFromPoint(index),
                lastP.y + offsetFromPoint(index)
            )
        }
    }

    private fun movePath(path: Path, x: Float, y: Float, offset: () -> Float) {
        val offSetPixel = offset()
        path.moveTo(x + offSetPixel, y + offSetPixel)
    }

    private fun quadPath(
        path: Path,
        firstX: Float,
        firstY: Float,
        secondX: Float,
        secondY: Float,
        offset: () -> Float
    ) {
        val offSetPixel = offset()
        path.quadTo(
            firstX + offSetPixel,
            firstY + offSetPixel,
            (firstX + secondX) / 2 + offSetPixel,
            (firstY + secondY) / 2 + offSetPixel
        )
    }

    private fun alphaNum(alpha: Float): Int = (255 * alpha).toInt()

    private fun backCountToDefault() {
        countToReset = 1
    }

    private fun offsetFromPoint(index: Int) = -(2 - index) * lineOffset
}