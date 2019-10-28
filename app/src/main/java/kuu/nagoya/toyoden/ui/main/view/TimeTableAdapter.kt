package kuu.nagoya.toyoden.ui.main.view

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableViewEntity

internal class TimeTableAdapter(
    private val context: Context
) : ListAdapter<TimeTableViewEntity, TimeTableViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        return TimeTableViewHolder.create(context, parent)
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        return holder.bindTo(getItem(position), context, onPullToReducing)
    }

    var onPullToReducing: OnPullToReducing? = null

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<TimeTableViewEntity>() {
            override fun areItemsTheSame(
                oldItem: TimeTableViewEntity,
                newItem: TimeTableViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TimeTableViewEntity,
                newItem: TimeTableViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}