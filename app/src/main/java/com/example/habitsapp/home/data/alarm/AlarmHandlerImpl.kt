package com.example.habitsapp.home.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.habitsapp.home.data.extension.toTimeStamp
import com.example.habitsapp.home.domain.alarm.AlarmHandler
import com.example.habitsapp.home.domain.models.Habit
import java.time.DayOfWeek
import java.time.ZonedDateTime

class AlarmHandlerImpl(private val context: Context) : AlarmHandler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun setRecurringAlarm(habit: Habit) {
        val nextOcurrance = calculateNextOcurrence(habit)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextOcurrance.toTimeStamp(),
            createPendingIntent(habit, nextOcurrance.dayOfWeek)
        )
    }

    /**
     * Creates a pending intent for the alarm.
     */
    private fun createPendingIntent(habit: Habit, dayOfWeek: DayOfWeek): PendingIntent? {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.HABIT_ID, habit.id)
        }
        return PendingIntent.getBroadcast(
            context,
            habit.id.hashCode() * 10 + dayOfWeek.value,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun calculateNextOcurrence(habit: Habit): ZonedDateTime {
        val today = ZonedDateTime.now()
        var nextOcurrence = ZonedDateTime.of(today.toLocalDate(), habit.reminder, today.zone)
        if (habit.frequency.contains(today.dayOfWeek) && today.isBefore(nextOcurrence)) {
            return nextOcurrence
        }
        do {
            nextOcurrence = nextOcurrence.plusDays(1)

        } while (!habit.frequency.contains(nextOcurrence.dayOfWeek))

        return nextOcurrence
    }

    override fun cancel(habit: Habit) {
       val nextOcurrance = calculateNextOcurrence(habit)
        val pending = createPendingIntent(habit, nextOcurrance.dayOfWeek)
        alarmManager.cancel(pending)
    }
}