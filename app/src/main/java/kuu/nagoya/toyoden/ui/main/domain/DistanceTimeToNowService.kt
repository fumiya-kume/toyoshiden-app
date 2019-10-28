package kuu.nagoya.toyoden.ui.main.domain

internal interface DistanceTimeToNowService {
    fun exec(hours: Int, minutes: Int): Long
}