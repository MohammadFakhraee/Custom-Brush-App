package ir.mohammadhf.custombrushapp.utils

import android.content.res.Resources
import android.graphics.BitmapFactory
import ir.mohammadhf.custombrushapp.R
import ir.mohammadhf.custombrushapp.brushes.Brush
import ir.mohammadhf.custombrushapp.brushes.bitmap.BitmapBrush
import ir.mohammadhf.custombrushapp.brushes.bitmap.RandDegBitmapBrush
import ir.mohammadhf.custombrushapp.brushes.circle.RandomSizeCircleBrush
import ir.mohammadhf.custombrushapp.brushes.circle.TubeBrush
import ir.mohammadhf.custombrushapp.brushes.connect.AllPointsBrush
import ir.mohammadhf.custombrushapp.brushes.connect.NearPointsBrush
import ir.mohammadhf.custombrushapp.brushes.marker.QuadMarkerBrush
import ir.mohammadhf.custombrushapp.brushes.marker.QuadMarkerGradientBrush
import ir.mohammadhf.custombrushapp.brushes.marker.QuadShadowMarkerBrush
import ir.mohammadhf.custombrushapp.brushes.pen.MultiLinePenBrush
import ir.mohammadhf.custombrushapp.brushes.pen.PenBrush
import ir.mohammadhf.custombrushapp.brushes.pen.ThickPenBrush
import ir.mohammadhf.custombrushapp.brushes.spray.RoundSprayBrush
import ir.mohammadhf.custombrushapp.brushes.spray.SquareSprayBrush

object BrushMaker {
    fun getBrush(id: Int, resources: Resources): Brush =
        when (id) {
            0 -> QuadMarkerBrush()
            1 -> QuadMarkerGradientBrush()
            2 -> QuadShadowMarkerBrush()
            3 -> BitmapBrush(BitmapFactory.decodeResource(resources, R.drawable.img_brush_1))
            4 -> RandDegBitmapBrush(BitmapFactory.decodeResource(resources, R.drawable.img_brush_1))
            5 -> TubeBrush()
            6 -> RandomSizeCircleBrush()
            7 -> AllPointsBrush()
            8 -> NearPointsBrush()
            9 -> MultiLinePenBrush()
            10 -> PenBrush()
            11 -> ThickPenBrush()
            12 -> SquareSprayBrush()
            13 -> RoundSprayBrush()
            else -> QuadMarkerBrush()
        }
}