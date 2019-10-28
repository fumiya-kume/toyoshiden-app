package kuu.nagoya.toyoden.ui.main.viewentity

import kuu.nagoya.toyoden.ui.main.domain.DistanceTimeToNowServiceImpl
import kuu.nagoya.toyoden.ui.main.entity.TimeTableItem


internal fun TimeTableItem.convert(): TimeTableViewEntity {

    val distanceTimeToNowService = DistanceTimeToNowServiceImpl()


    return TimeTableViewEntity(
        this.id,
        this.tramStationName.name,
        this.timeList.mapIndexed { index, timeTableTime ->
            val distanceTimeToNow = distanceTimeToNowService.exec(
                timeTableTime.hours,
                timeTableTime.minutes
            )

            TimeTableTimeItemViewEntity(
                index,
                "${timeTableTime.hours}:${timeTableTime.minutes}",
                "$distanceTimeToNow 分後"
            )
        },
        this.timeList.elementAtOrNull(2).run { "${this?.hours ?: 0}: ${this?.minutes ?: 0}" },
        this.tramStationName.lat,
        this.tramStationName.lon
    )
}

internal fun List<TimeTableItem>.convert(): List<TimeTableViewEntity> {
    return this.map { it.convert() }
}