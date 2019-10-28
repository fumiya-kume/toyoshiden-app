package kuu.nagoya.toyoden.ui.main.entity

internal data class TimeTableItem(
    val id: Int,
    val tramStationName: TramStation,
    val timeList: List<TimeTableTime>
)