package ir.mohammadhf.custombrushapp.holder

import android.graphics.Canvas
import android.view.SurfaceHolder
import ir.mohammadhf.custombrushapp.holder.ISurfaceHolder

class SurfaceViewHolder(private val surfaceHolder: SurfaceHolder) :
    ISurfaceHolder {

    override val surface
        get() = surfaceHolder.surface!!

    override fun lockCanvas(): Canvas? =
        surfaceHolder.lockCanvas()

    override fun unlockCanvasAndPost(canvas: Canvas?) =
        surfaceHolder.unlockCanvasAndPost(canvas)
}