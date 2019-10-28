package kuu.nagoya.toyoden.ui.main.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.toyoden.ui.main.infra.LoadTimeTableReadonlyRepositoryImpl
import kuu.nagoya.toyoden.ui.main.usecase.LoadTimeTableReadonlyRepository
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableTimeItemViewEntity
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableViewEntity
import java.util.*

internal class TimeTableLiveData(
    private val coroutineScope: CoroutineScope,
    private val timeTableReadonlyRepository: LoadTimeTableReadonlyRepository = LoadTimeTableReadonlyRepositoryImpl()
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
                            val now = Calendar.getInstance().apply {
                                this.time = Date(System.currentTimeMillis())
                            }

                            val target = Calendar.getInstance().apply {
                                this.time = Date(System.currentTimeMillis())

                                this.set(
                                    this.get(Calendar.YEAR),
                                    this.get(Calendar.MONTH),
                                    this.get(Calendar.DAY_OF_MONTH),
                                    timeTableTime.hours,
                                    timeTableTime.minutes
                                )
                            }

                            val diferrence = target.time.time - now.time.time
                            val result =
                                java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(diferrence)


                            TimeTableTimeItemViewEntity(
                                index,
                                "${timeTableTime.hours}:${timeTableTime.minutes}",
                                "${result} 分後"
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