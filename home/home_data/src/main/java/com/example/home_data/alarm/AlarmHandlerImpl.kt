package com.example.home_data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.home_data.extension.toTimeStamp
import com.example.home_domain.alarm.AlarmHandler
import com.example.home_domain.models.Habit
import java.time.DayOfWeek
import java.time.ZonedDateTime

class AlarmHandlerImpl(private val context: Context) : com.example.home_domain.alarm.AlarmHandler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun setRecurringAlarm(habit: com.example.home_domain.models.Habit) {
        val nextOcurrance = calculateNextOcurrence(habit)
        createPendingIntent(habit, nextOcurrance.dayOfWeek)?.let {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                nextOcurrance.toTimeStamp(),
                it
            )
        }
    }

    /**
     * Creates a pending intent for the alarm.
     */
    private fun createPendingIntent(habit: com.example.home_domain.models.Habit, dayOfWeek: DayOfWeek): PendingIntent? {
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

    /**
     * Calculates the next ocurrence of the habit alarm based on the day of week and time of day.
     * If the habit is set to remind on the current day of the week and the time of day is in the future, it will return the current day.
     * Otherwise, it will return the next day of the week that the habit is set to remind on.
     *
     */
    private fun calculateNextOcurrence(habit: com.example.home_domain.models.Habit): ZonedDateTime {
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

    override fun cancel(habit: com.example.home_domain.models.Habit) {
       val nextOcurrance = calculateNextOcurrence(habit)
        val pending = createPendingIntent(habit, nextOcurrance.dayOfWeek)
        if (pending != null) {
            alarmManager.cancel(pending)
        }
    }
}