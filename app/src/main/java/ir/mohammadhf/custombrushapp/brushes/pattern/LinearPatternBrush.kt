package ir.mohammadhf.custombrushapp.brushes.pattern

import android.graphics.*
import ir.mohammadhf.custombrushapp.brushes.Brush
import ir.mohammadhf.custombrushapp.brushes.marker.QuadMarkerBrush

class LinearPatternBrush : QuadMarkerBrush() {
    init {
        paint.shader = LinearGradient(
            0f, 0f, 40f, 0f, Color.BLACK, paint.color, Shader.TileMode.MIRROR
        )
    }
}