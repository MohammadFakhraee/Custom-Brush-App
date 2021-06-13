package ir.mohammadhf.custombrushapp.brushes.sliced

class MultiLineBrush: SlicedBrush() {
    init {
        paint.strokeWidth = 4f
    }

    override val lineOffset: Float
        get() = paint.strokeWidth * 1.5f
}