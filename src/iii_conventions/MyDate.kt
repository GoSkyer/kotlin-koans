package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val calendar1 = Calendar.getInstance();
        val calendar2 = Calendar.getInstance();
        calendar1.set(this.year, this.month, this.dayOfMonth)
        calendar2.set(other.year, other.month, other.dayOfMonth)
        return (calendar1.timeInMillis - calendar2.timeInMillis).toInt()
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)
