package ir.mohammadhf.custombrushapp.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import ir.mohammadhf.custombrushapp.brushes.Brush
import ir.mohammadhf.custombrushapp.brushes.marker.QuadMarkerBrush

class PaintManager(paintBrush: Brush = QuadMarkerBrush()) :
    ITouchListener {
    lateinit var drawnBitmap: Bitmap
    lateinit var drawnCanvas: Canvas
    var paintBrush = paintBrush
        set(value) {
            field = value
            field.changeColor(selectedColor)
        }

    private var selectedColor = Color.BLACK

    fun onSizeChange(width: Int, height: Int) {
        drawnBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        drawnCanvas = Canvas(drawnBitmap)
        drawnCanvas.drawColor(Color.WHITE)
    }

    override fun onTouchDown(x: Float, y: Float) {
        paintBrush.onTouchDown(x, y)
    }

    override fun onTouchMove(x: Float, y: Float) {
        paintBrush.onTouchMove(x, y)
    }

    override fun onTouchUp(x: Float, y: Float) {
        paintBrush.onTouchUp(x, y)
    }

    override fun drawOn(paintCanvas: Canvas) {
        paintCanvas.drawBitmap(drawnBitmap, 0f, 0f, null)
        if (paintBrush.shouldReset()) {
            paintBrush.drawOn(drawnCanvas)
            paintBrush.reset()
        } else
            paintBrush.drawOn(paintCanvas)
    }

    fun changeColor(newColor: Int) {
        paintBrush.changeColor(newColor)
        selectedColor = newColor
    }
}