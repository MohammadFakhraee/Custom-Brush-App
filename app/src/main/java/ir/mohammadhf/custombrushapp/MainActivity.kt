package ir.mohammadhf.custombrushapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadhf.custombrushapp.brushes.Brush
import ir.mohammadhf.custombrushapp.brushes.marker.QuadMarkerBrush
import ir.mohammadhf.custombrushapp.databinding.ActivityMainBinding
import ir.mohammadhf.custombrushapp.utils.DataFakeGenerator
import ir.mohammadhf.custombrushapp.utils.PaintManager
import ir.mohammadhf.custombrushapp.view.BrushListAdapter
import ir.mohammadhf.custombrushapp.view.OnSelectedBrush

class MainActivity : AppCompatActivity() {

    private val paintManger: PaintManager =
        PaintManager(
            QuadMarkerBrush()
        )

    private val brushAdapter = BrushListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {
            paintView.paintManager = paintManger

            colorPicker.subscribe { color: Int, _: Boolean, _: Boolean ->
                paintManger.changeColor(color)
            }

            recyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.VERTICAL, false
            )
            recyclerView.adapter = brushAdapter
        }

        brushAdapter.submitList(DataFakeGenerator.listOfBrushes)
        brushAdapter.onSelectedBrush = object : OnSelectedBrush {
            override fun nextBrush(brush: Brush) {
                paintManger.paintBrush = brush
            }
        }
    }
}
