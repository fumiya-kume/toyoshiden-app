package kuu.nagoya.toyoden.ui.main.viewentity

internal data class TimeTableViewEntity(
    val id: Int,
    val stationName: String,
    val timeList: List<TimeTableTimeItemViewEntity>
)