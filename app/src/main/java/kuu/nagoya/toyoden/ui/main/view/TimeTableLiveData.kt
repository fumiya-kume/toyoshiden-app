package kuu.nagoya.toyoden.ui.main.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.toyoden.ui.main.domain.DistanceTimeToNowService
import kuu.nagoya.toyoden.ui.main.domain.DistanceTimeToNowServiceImpl
import kuu.nagoya.toyoden.ui.main.infra.LoadTimeTableReadonlyRepositoryImpl
import kuu.nagoya.toyoden.ui.main.usecase.LoadTimeTableReadonlyRepository
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableTimeItemViewEntity
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableViewEntity

internal class TimeTableLiveData(
    private val coroutineScope: CoroutineScope,
    private val timeTableReadonlyRepository: LoadTimeTableReadonlyRepository = LoadTimeTableReadonlyRepositoryImpl(),
    private val distanceTimeToNowService: DistanceTimeToNowService = DistanceTimeToNowServiceImpl()
) : LiveData<List<TimeTableViewEntity>>() {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            val timeTable = timeTableReadonlyRepository
                .loadTimeTable()
                .tableItemList
                .mapIndexed { index, timeTableItem ->
                    TimeTableViewEntity(
                        index,
                        timeTableItem.tramStationName.name,
                        timeTableItem.timeList.mapIndexed { index, timeTableTime ->
                            TimeTableTimeItemViewEntity(
                                index,
                                "${timeTableTime.hours}:${timeTableTime.minutes}",
                                "${distanceTimeToNowService.exec(
                                    timeTableTime.hours,
                                    timeTableTime.minutes
                                )} 分後"
                            )
                        },
                        timeTableItem.timeList.elementAt(2).run { "${this.hours}: ${this.minutes}" },
                        timeTableItem.tramStationName.lat,
                        timeTableItem.tramStationName.lon
                    )
                }
            postValue(timeTable)
        }
    }
}