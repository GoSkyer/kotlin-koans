package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val calendar1 = Calendar.getInstance();
        val calendar2 = Calendar.getInstance();
        calendar1.set(this.year, this.month, this.dayOfMonth)
        calendar2.set(other.year, other.month, other.dayOfMonth)
        return ((calendar1.timeInMillis - calendar2.timeInMillis) / 1000).toInt()
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
//        return DateIterator(this)
        return object : Iterator<MyDate> {
            var current: MyDate = this@DateRange.start
            override fun hasNext(): Boolean {
                return current <= this@DateRange.endInclusive
            }

            override fun next(): MyDate {
                val result = current
                current = current.nextDay()
                return result
            }
        }
    }

    operator fun contains(item: MyDate): Boolean {
        return start < item && endInclusive > item
    }
}

//
//class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate> {
//}
class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}