package ir.mohammadhf.custombrushapp.brushes.pen

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import ir.mohammadhf.custombrushapp.brushes.Brush
import kotlin.random.Random

open class MultiLinePenBrush : Brush() {
    init {
        paint.strokeWidth = 4f
    }

    open val minPixelRange = 0
    open val maxPixelRange = 8

    val randomMaker = Random(1)
    private val randomNum
        get() = randomMaker.nextInt(minPixelRange, maxPixelRange).toFloat()

    lateinit var currentPath: Path
    val pathList = ArrayList<Path>()

    val lastP = PointF()

    override fun onTouchDown(x: Float, y: Float) {
        currentPath = Path()
        currentPath.moveTo(x, y)
        lastP.x = x
        lastP.y = y
    }

    override fun onTouchMove(x: Float, y: Float) {
        shouldReset = true

        currentPath = Path()
        currentPath.moveTo(lastP.x, lastP.y)
        currentPath.lineTo(x, y)
        currentPath.moveTo(
            lastP.x - randomNum,
            lastP.y - randomNum
        )
        currentPath.lineTo(
            x - randomNum,
            y - randomNum
        )
        currentPath.moveTo(
            lastP.x + randomNum,
            lastP.y + randomNum
        )
        currentPath.lineTo(
            x + randomNum,
            y + randomNum
        )
        pathList.add(currentPath)

        lastP.x = x
        lastP.y = y
    }

    override fun onTouchUp(x: Float, y: Float) {
        shouldReset = true
        currentPath.lineTo(x, y)
        pathList.add(currentPath)
    }

    override fun drawOn(paintCanvas: Canvas) {
        for (path in pathList)
            paintCanvas.drawPath(path, paint)
    }

    override fun resetBrush() {
        pathList.clear()
    }
}