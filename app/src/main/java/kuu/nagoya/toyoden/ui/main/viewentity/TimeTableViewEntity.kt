package kuu.nagoya.toyoden.ui.main.viewentity

internal data class TimeTableViewEntity(
    val id: Int,
    val stationName: String,
    val timeList: List<TimeTableTimeItemViewEntity>,
    val summaryTimeTable: String,
    val lat: Double,
    val lon: Double,
    val isVisibleAllTimeTable: Boolean = false
)