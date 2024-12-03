package jp.co.example.karaagenotification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class CalendarViewModel : ViewModel() {

    private val _yearMonth = MutableStateFlow("")
    val yearMonth: StateFlow<String> get() = _yearMonth

    private val _days = MutableStateFlow<List<Day>>(emptyList())
    val days: StateFlow<List<Day>> get() = _days

    private val _selectedDay = MutableStateFlow<Day?>(null)
    val selectedDay: StateFlow<Day?> get() = _selectedDay

    private val calendar = Calendar.getInstance()

    init {
        updateCalendar()
    }

    private fun updateCalendar() {
        viewModelScope.launch {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            _yearMonth.value = "${year}年${month}月"

            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            val daysList = mutableListOf<Day>()
            for (day in 1..daysInMonth) {
                val isHoliday = isHoliday(year, month, day)
                daysList.add(Day(day, isHoliday))
            }
            _days.value = daysList
        }
    }

    private fun isHoliday(year: Int, month: Int, day: Int): Boolean {
        // Implement holiday logic here
        return false
    }

    fun selectDay(day: Day) {
        _selectedDay.value = day
    }

    fun nextMonth() {
        calendar.add(Calendar.MONTH, 1)
        updateCalendar()
    }

    fun previousMonth() {
        calendar.add(Calendar.MONTH, -1)
        updateCalendar()
    }
}

data class Day(val day: Int, val isHoliday: Boolean)
