package kuu.nagoya.toyoden.ui.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.toyoden.databinding.ItemTimeTableBinding
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableViewEntity

internal class TimeTableViewHolder private constructor(
    private val binding: ItemTimeTableBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            context: Context,
            container: ViewGroup
        ): TimeTableViewHolder =
            TimeTableViewHolder(
                ItemTimeTableBinding.inflate(
                    LayoutInflater.from(context),
                    container,
                    false
                )
            )
    }

    fun bindTo(
        timeTableViewEntity: TimeTableViewEntity,
        context: Context,
        onPullToReducing: OnPullToReducing?
    ) {

        binding.viewentity = timeTableViewEntity

        val timeTableAdapter = TimeTableTimeItemAdapter(context)
        timeTableAdapter.submitList(timeTableViewEntity.timeList)

        binding.itemTimeTableTimetableRecyclerView.adapter = timeTableAdapter

        val layoutManger = LinearLayoutManager(context)
        binding.itemTimeTableTimetableRecyclerView.layoutManager = layoutManger
        binding.itemTimeTableTimetableRecyclerView.scrollToPosition(1)

        binding.itemTimeTableTimetableRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val isVisibleFirstItem = layoutManger.findFirstVisibleItemPosition() == 0
                if (isVisibleFirstItem && dy < -10) {
                    onPullToReducing?.onPull()

                    recyclerView.layoutManager?.scrollToPosition(0)
                }
            }
        })

        if (timeTableViewEntity.isVisibleAllTimeTable) {
            binding.itemTimeTableMainTimeTableTextView.visibility = View.GONE
            binding.itemTimeTableTimetableRecyclerView.visibility = View.VISIBLE
        } else {
            binding.itemTimeTableMainTimeTableTextView.visibility = View.VISIBLE
            binding.itemTimeTableTimetableRecyclerView.visibility = View.GONE
        }
    }
}