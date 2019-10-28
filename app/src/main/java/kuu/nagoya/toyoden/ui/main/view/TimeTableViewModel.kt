package kuu.nagoya.toyoden.ui.main.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class TimeTableViewModel : ViewModel() {
    private var _timeTableLiveData: TimeTableLiveData = TimeTableLiveData(viewModelScope)
    val timeTableLiveData = _timeTableLiveData
}
