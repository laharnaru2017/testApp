package com.test.setalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var btnSetAlarm: Button
    lateinit var timePicker: TimePicker
    lateinit var editText: EditText
    lateinit var tButton: ToggleButton
    var pendingIntent:PendingIntent? = null
    var alarmManager: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSetAlarm = findViewById(R.id.button)
        timePicker = findViewById(R.id.timePicker)
        editText = findViewById(R.id.time)
        tButton = findViewById(R.id.tButton)

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        /*btnSetAlarm.setOnClickListener {
            val i: Int = editText.getText().toString().toInt()
            val timeing = System.currentTimeMillis() + i * 1000
            Log.d("TIMING_LOG", timeing.toString())
            startAlert(timeing)
        }*/
        tButton.setOnClickListener {
            if (tButton.isChecked){
                Log.d("ALARAM_NOT", "Start")
                val calendar: Calendar = Calendar.getInstance()
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.hour,
                        timePicker.minute,
                        0
                    )
                } else {
                    calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.currentHour,
                        timePicker.currentMinute, 0
                    )
                }

                /*alarmMgr = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmIntent = Intent(applicationContext, MyBroadcastReceiver::class.java).let { intent ->
                    PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
                }
                alarmMgr?.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    calendar.timeInMillis,
                    alarmIntent
                )*/

                var intent = Intent(this, MyBroadcastReceiver::class.java)
                pendingIntent =
                    PendingIntent.getBroadcast(this.applicationContext, 234324243, intent, 0)
                alarmManager!![AlarmManager.RTC_WAKEUP, calendar.timeInMillis] =
                    pendingIntent
                //alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 0, pendingIntent)
                Toast.makeText(this, "Alarm set", Toast.LENGTH_LONG).show()

                //startAlert(calendar.timeInMillis)
                Log.d("TIMING_LOG", calendar.timeInMillis.toString())
            } else {
                Log.d("ALARAM_NOT", "Stop")
                alarmManager!!.cancel(pendingIntent)
            }

        }

    }

    private fun startAlert(timeing: Long) {
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        pendingIntent =
            PendingIntent.getBroadcast(this.applicationContext, 234324243, intent, 0)
        alarmManager!![AlarmManager.RTC_WAKEUP, timeing] =
            pendingIntent
        Toast.makeText(this, "Alarm set", Toast.LENGTH_LONG).show()
    }

}