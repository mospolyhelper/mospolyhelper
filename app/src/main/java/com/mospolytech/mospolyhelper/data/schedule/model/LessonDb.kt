package com.mospolytech.mospolyhelper.data.schedule.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDate

@Entity
data class LessonDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lesson_id")
    val id: Int,
    val dayOfWeek: DayOfWeek,
    val order: Int,
    val isEvening: Boolean,
    val title: String,
    val type: String,
    val dateFrom: LocalDate,
    val dateTo: LocalDate
)