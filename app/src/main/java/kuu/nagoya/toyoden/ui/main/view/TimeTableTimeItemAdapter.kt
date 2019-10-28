package kuu.nagoya.toyoden.ui.main.view

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableTimeItemViewEntity

internal class TimeTableTimeItemAdapter(
    private val context: Context
) : androidx.recyclerview.widget.ListAdapter<TimeTableTimeItemViewEntity, TimeTableTimeItemViewHodler>(
    DIFF_UTIL
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableTimeItemViewHodler {
        return TimeTableTimeItemViewHodler.create(
            context,
            parent
        )
    }

    override fun onBindViewHolder(holder: TimeTableTimeItemViewHodler, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<TimeTableTimeItemViewEntity>() {
            override fun areItemsTheSame(
                oldItem: TimeTableTimeItemViewEntity,
                newItem: TimeTableTimeItemViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TimeTableTimeItemViewEntity,
                newItem: TimeTableTimeItemViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}