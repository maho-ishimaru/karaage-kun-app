package jp.co.example.karaagenotification

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.util.*

class CalendarRepository {

    suspend fun fetchHolidays(year: Int, month: Int): List<Day> {
        return withContext(Dispatchers.IO) {
            val holidays = mutableListOf<Day>()
            try {
                val url = "https://example.com/holidays?year=$year&month=$month"
                val document = Jsoup.connect(url).get()
                val elements = document.select("holiday")
                for (element in elements) {
                    val day = element.attr("day").toInt()
                    holidays.add(Day(day, true))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            holidays
        }
    }

    suspend fun fetchHolidaysFromAPI(year: Int, month: Int): List<Day> {
        return withContext(Dispatchers.IO) {
            val holidays = mutableListOf<Day>()
            try {
                val url = "https://public-holiday-api.com/api/v1/holidays?year=$year&month=$month"
                val document = Jsoup.connect(url).get()
                val elements = document.select("holiday")
                for (element in elements) {
                    val day = element.attr("day").toInt()
                    holidays.add(Day(day, true))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            holidays
        }
    }
}
