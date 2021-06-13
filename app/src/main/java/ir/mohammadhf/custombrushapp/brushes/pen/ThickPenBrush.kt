package ir.mohammadhf.custombrushapp.brushes.pen

import android.graphics.Paint
import ir.mohammadhf.custombrushapp.brushes.marker.QuadMarkerBrush

class ThickPenBrush : QuadMarkerBrush() {
    init {
        paint.strokeCap = Paint.Cap.BUTT
        isQuad = false
    }
}