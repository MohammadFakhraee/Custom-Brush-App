package ir.mohammadhf.custombrushapp.utils

import android.graphics.Canvas

interface ITouchListener {
    fun onTouchDown(x: Float, y: Float)
    fun onTouchMove(x: Float, y: Float)
    fun onTouchUp(x: Float, y: Float)
    fun drawOn(paintCanvas: Canvas)
}