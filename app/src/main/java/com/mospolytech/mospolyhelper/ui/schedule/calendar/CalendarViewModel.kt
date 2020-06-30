package com.mospolytech.mospolyhelper.ui.schedule.calendar

import com.mospolytech.mospolyhelper.repository.schedule.models.Schedule
import com.mospolytech.mospolyhelper.ui.common.ViewModelBase
import com.mospolytech.mospolyhelper.ui.common.ViewModelMessage
import com.mospolytech.mospolyhelper.ui.schedule.ScheduleViewModel
import com.mospolytech.mospolyhelper.utils.StaticDI
import java.time.LocalDate

class CalendarViewModel :
    ViewModelBase(StaticDI.viewModelMediator, CalendarViewModel::class.java.simpleName) {
    companion object {
        const val CalendarMode = "CalendarMode"
    }
    var schedule: Schedule? = null
    var scheduleFilter: Schedule.Filter = Schedule.Filter.default
    var date: LocalDate = LocalDate.now()
    var isAdvancedSearch: Boolean = false

    init {
        subscribe(::handleMessage)
    }

    private fun handleMessage(message: ViewModelMessage) {
        when (message.key) {
            CalendarMode -> {
                val list = message.content as List<*>
                schedule = list[0] as Schedule
                scheduleFilter = list[1] as Schedule.Filter
                date = list[2] as LocalDate
                isAdvancedSearch = list[3] as Boolean
            }
        }
    }

    fun dateChanged() {
        send(ScheduleViewModel::class.java.simpleName, "ChangeDate", listOf(date))
    }
}
