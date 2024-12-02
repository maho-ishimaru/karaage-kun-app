package jp.co.example.karaagenotification

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CalendarRepository {

    suspend fun fetchHolidays(year: Int, month: Int): List<Day> {
        return withContext(Dispatchers.IO) {
            // Simulate fetching holiday data
            val holidays = mutableListOf<Day>()
            if (month == 1 && year == 2023) {
                holidays.add(Day(1, true)) // New Year's Day
            }
            holidays
        }
    }
}
