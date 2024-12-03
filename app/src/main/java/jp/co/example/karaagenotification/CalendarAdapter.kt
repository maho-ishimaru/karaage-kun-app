package jp.co.example.karaagenotification

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(
    private val days: List<Day>,
    private val onDayClick: (Day) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    private var selectedDay: Day? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = days[position]
        holder.bind(day, day == selectedDay)
        holder.itemView.setOnClickListener {
            selectedDay = day
            notifyDataSetChanged()
            onDayClick(day)
        }
    }

    override fun getItemCount(): Int = days.size

    inner class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)

        fun bind(day: Day, isSelected: Boolean) {
            dayTextView.text = day.day.toString()
            if (day.isHoliday) {
                dayTextView.setTextColor(Color.RED)
            } else {
                dayTextView.setTextColor(Color.BLACK)
            }
            if (isSelected) {
                dayTextView.setBackgroundResource(R.drawable.selected_day_background)
            } else {
                dayTextView.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }
}
