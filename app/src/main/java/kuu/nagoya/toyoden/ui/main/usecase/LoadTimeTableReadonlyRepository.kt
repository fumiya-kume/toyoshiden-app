package kuu.nagoya.toyoden.ui.main.usecase

import kuu.nagoya.toyoden.ui.main.entity.TimeTable

internal interface LoadTimeTableReadonlyRepository {
    suspend fun loadTimeTable(): TimeTable
}