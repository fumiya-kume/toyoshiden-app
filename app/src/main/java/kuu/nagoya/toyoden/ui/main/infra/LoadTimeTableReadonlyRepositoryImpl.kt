package kuu.nagoya.toyoden.ui.main.infra

import kuu.nagoya.toyoden.ui.main.entity.TimeTable
import kuu.nagoya.toyoden.ui.main.entity.TimeTableItem
import kuu.nagoya.toyoden.ui.main.entity.TimeTableTime
import kuu.nagoya.toyoden.ui.main.entity.TramStation
import kuu.nagoya.toyoden.ui.main.usecase.LoadTimeTableReadonlyRepository

internal class LoadTimeTableReadonlyRepositoryImpl : LoadTimeTableReadonlyRepository {
    override suspend fun loadTimeTable(): TimeTable {
        return TimeTable(
            0,
            listOf(
                TimeTableItem(
                    0,
                    TramStation(
                        0,
                        "豊橋駅前",
                        34.762734,
                        137.382128
                    ),
                    listOf(
                        TimeTableTime(
                            0,
                            3,
                            10
                        ),
                        TimeTableTime(
                            0,
                            8,
                            20
                        ),
                        TimeTableTime(
                            0,
                            8,
                            30
                        )
                    )
                ),
                TimeTableItem(
                    1,
                    TramStation(
                        0,
                        "新川",
                        34.762857,
                        137.389483
                    ),
                    listOf(
                        TimeTableTime(
                            0,
                            8,
                            10
                        ),
                        TimeTableTime(
                            0,
                            8,
                            20
                        ),
                        TimeTableTime(
                            0,
                            8,
                            30
                        )
                    )
                )
            )
        )
    }
}