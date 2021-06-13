package ir.mohammadhf.custombrushapp.brushes.sliced

class AlphaMultiLineBrush : AlphaSlicedBrush() {
    init {
        paint.strokeWidth = 4f
    }
    override val lineOffset: Float
        get() = paint.strokeWidth * 1.5f
}