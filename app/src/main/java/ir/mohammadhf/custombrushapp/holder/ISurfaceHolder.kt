package ir.mohammadhf.custombrushapp.holder

import android.graphics.Canvas
import android.view.Surface

interface ISurfaceHolder {
    val surface: Surface

    fun lockCanvas(): Canvas?

    fun unlockCanvasAndPost(canvas: Canvas?)
}