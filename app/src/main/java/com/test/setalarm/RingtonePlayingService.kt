package com.test.setalarm

import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder

class RingtonePlayingService : Service() {
    lateinit var ringtone: Ringtone

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //val ringtoneUri: Uri = Uri.parse(intent!!.extras!!.getString("ringtone-uri"))
        // setting default ringtone
        //ringtone = RingtoneManager.getRingtone(applicationContext, ringtoneUri)
        var alarmUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        val ringtone = RingtoneManager.getRingtone(applicationContext, alarmUri)
        // play ringtone

        // play ringtone
        ringtone.play()

        return START_NOT_STICKY;
    }

    override fun onDestroy() {
        super.onDestroy()
        ringtone.stop()
    }
}