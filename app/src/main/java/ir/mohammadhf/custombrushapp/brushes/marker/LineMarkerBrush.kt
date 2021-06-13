package ir.mohammadhf.custombrushapp.brushes.marker

class LineMarkerBrush : QuadMarkerBrush() {

    init {
        isQuad = false
        paint.alpha = 50
    }
}