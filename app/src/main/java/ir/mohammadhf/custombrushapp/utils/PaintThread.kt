package ir.mohammadhf.custombrushapp.utils

import android.graphics.Canvas
import ir.mohammadhf.custombrushapp.holder.ISurfaceHolder
import ir.mohammadhf.custombrushapp.utils.PaintManager

class PaintThread(
    private val surfaceHolder: ISurfaceHolder,
    private val paintManager: PaintManager
) : Thread() {
    var isRunning = false

    override fun run() {
        while (isRunning) {
            if (surfaceHolder.surface.isValid) {
                var canvas: Canvas? = null

                try {
                    canvas = surfaceHolder.lockCanvas()
                    synchronized(surfaceHolder) {
                        if (canvas != null)
                            paintManager.drawOn(canvas)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }
}