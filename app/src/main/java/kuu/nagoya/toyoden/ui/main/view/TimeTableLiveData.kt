package kuu.nagoya.toyoden.ui.main.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.toyoden.ui.main.infra.LoadTimeTableReadonlyRepositoryImpl
import kuu.nagoya.toyoden.ui.main.usecase.LoadTimeTableReadonlyRepository
import kuu.nagoya.toyoden.ui.main.viewentity.TimeTableViewEntity
import kuu.nagoya.toyoden.ui.main.viewentity.convert

internal class TimeTableLiveData(
    coroutineScope: CoroutineScope,
    private val timeTableReadonlyRepository: LoadTimeTableReadonlyRepository = LoadTimeTableReadonlyRepositoryImpl()
) : LiveData<List<TimeTableViewEntity>>() {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            postValue(
                timeTableReadonlyRepository
                    .loadTimeTable()
                    .tableItemList
                    .convert()
            )
        }
    }
}