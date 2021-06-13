package ir.mohammadhf.custombrushapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadhf.custombrushapp.BrushModel
import ir.mohammadhf.custombrushapp.R
import ir.mohammadhf.custombrushapp.brushes.Brush
import ir.mohammadhf.custombrushapp.databinding.ItemBrushBinding
import ir.mohammadhf.custombrushapp.utils.BrushMaker

class BrushListAdapter :
    ListAdapter<BrushModel, BrushListAdapter.BrushViewHolder>(BrushDiffUtil()) {

    var onSelectedBrush: OnSelectedBrush? = null

    var currentSelectedPos = 0
    var prevSelectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushViewHolder =
        BrushViewHolder(
            ItemBrushBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BrushViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class BrushViewHolder(private val binding: ItemBrushBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(brushModel: BrushModel) {

            binding.run {
                brushName.text = brushModel.name

                if (adapterPosition == currentSelectedPos)
                    root.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context, R.color.gray
                        )
                    )
                else
                    root.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context, R.color.white
                        )
                    )

                root.setOnClickListener {
                    onSelectedBrush?.nextBrush(
                        BrushMaker.getBrush(
                            brushModel.id, root.resources
                        )
                    )

                    prevSelectedPos = currentSelectedPos
                    currentSelectedPos = adapterPosition

                    notifyItemChanged(prevSelectedPos)
                    notifyItemChanged(currentSelectedPos)
                }
            }
        }
    }
}

class BrushDiffUtil : DiffUtil.ItemCallback<BrushModel>() {
    override fun areItemsTheSame(oldItem: BrushModel, newItem: BrushModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BrushModel, newItem: BrushModel): Boolean =
        oldItem == newItem

}

interface OnSelectedBrush {
    fun nextBrush(brush: Brush)
}