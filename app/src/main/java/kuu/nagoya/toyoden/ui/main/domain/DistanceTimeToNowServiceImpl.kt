package kuu.nagoya.toyoden.ui.main.domain

import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

class DistanceTimeToNowServiceImpl : DistanceTimeToNowService {

    private fun Date.setHoursAndMinutes(hours: Int, minutes: Int): Calendar {
        val time = this
        return Calendar
            .getInstance()
            .apply {
                this.time = time

                this.set(
                    this.get(Calendar.YEAR),
                    this.get(Calendar.MONTH),
                    this.get(Calendar.DAY_OF_MONTH),
                    hours,
                    minutes
                )
            }
    }

    override fun exec(hours: Int, minutes: Int): Long {
        val now = Calendar.getInstance().apply {
            this.time = Date(System.currentTimeMillis())
        }

        val target =
            Date(System.currentTimeMillis())
                .setHoursAndMinutes(
                    hours,
                    minutes
                )

        return TimeUnit.MILLISECONDS.toMinutes(target.time.time - now.time.time)
    }
}