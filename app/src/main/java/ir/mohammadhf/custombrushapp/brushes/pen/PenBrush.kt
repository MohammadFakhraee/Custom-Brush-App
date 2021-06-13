package ir.mohammadhf.custombrushapp.brushes.pen

import android.graphics.Canvas
import android.graphics.Path

open class PenBrush : MultiLinePenBrush() {
    open val minStroke = 9
    open val maxStroke = 18

    open val randomStroke
        get() = randomMaker.nextInt(minStroke, maxStroke).toFloat()

    private val strokeSizeList = ArrayList<Float>()

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true
        currentPath = Path()
        currentPath.moveTo(lastP.x, lastP.y)
        currentPath.lineTo(x, y)
        pathList.add(currentPath)
        strokeSizeList.add(randomStroke)

        lastP.x = x
        lastP.y = y
    }

    override fun onTouchUp(x: Float, y: Float) {
        super.onTouchUp(x, y)
        strokeSizeList.add(randomStroke)
    }

    override fun drawOn(paintCanvas: Canvas) {
        for (path in pathList) {
            paint.strokeWidth = randomStroke
            paintCanvas.drawPath(path, paint)
        }
    }
}