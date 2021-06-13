package ir.mohammadhf.custombrushapp.holder

import android.graphics.Canvas
import android.view.Surface
import android.view.TextureView
import ir.mohammadhf.custombrushapp.holder.ISurfaceHolder

class TextureViewHolder(private val textureView: TextureView) :
    ISurfaceHolder {
    override val surface: Surface
        get() = Surface(textureView.surfaceTexture)

    override fun lockCanvas(): Canvas? =
        textureView.lockCanvas()

    override fun unlockCanvasAndPost(canvas: Canvas?) {
        textureView.unlockCanvasAndPost(canvas)
    }
}