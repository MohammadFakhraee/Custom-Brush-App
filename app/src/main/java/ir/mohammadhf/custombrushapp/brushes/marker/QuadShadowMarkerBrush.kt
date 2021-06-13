package ir.mohammadhf.custombrushapp.brushes.marker

import android.graphics.Canvas
import android.graphics.Path
import ir.mohammadhf.custombrushapp.brushes.Brush

class QuadShadowMarkerBrush : QuadMarkerBrush() {
    companion object {
        const val SHADOW_RADIUS = 20f
    }

    override fun changeColor(newColor: Int) {
        super.changeColor(newColor)
        paint.setShadowLayer(SHADOW_RADIUS, 0f, 0f, paint.color)
    }
}