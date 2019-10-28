package kuu.nagoya.toyoden.ui.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.toyoden.databinding.ItemTimeTableTimeItemBinding
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableTimeItemViewEntity

internal class TimeTableTimeItemViewHodler private constructor(
    private val binding: ItemTimeTableTimeItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            context: Context,
            container: ViewGroup
        ): TimeTableTimeItemViewHodler =
            TimeTableTimeItemViewHodler(
                ItemTimeTableTimeItemBinding.inflate(
                    LayoutInflater.from(context),
                    container,
                    false
                )
            )
    }

    fun bindTo(viewentity: TimeTableTimeItemViewEntity) {
        binding.viewentity = viewentity
    }

}