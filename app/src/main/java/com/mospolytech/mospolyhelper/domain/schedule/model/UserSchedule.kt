package com.mospolytech.mospolyhelper.domain.schedule.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class UserSchedule : Comparable<UserSchedule> {
    abstract val title: String

    override fun compareTo(other: UserSchedule): Int {
        return title.compareTo(other.title)
    }
}

@Serializable
@SerialName("student")
data class StudentSchedule(
    val id: String,
    override val title: String
) : UserSchedule()

@Serializable
@SerialName("teacher")
data class TeacherSchedule(
    val id: String,
    override val title: String
) : UserSchedule()

@Serializable
@SerialName("auditorium")
data class AuditoriumSchedule(
    val id: String,
    override val title: String
) : UserSchedule()

@Serializable
@SerialName("advancedSearch")
data class AdvancedSearchSchedule(
    val titles: Set<String>? = null,
    val types: Set<String>? = null,
    val auditoriums: Set<String>? = null,
    val teachers: Set<String>? = null,
    val groups: Set<String>? = null
) {
    val title: String = "Продвинутый поиск"
}