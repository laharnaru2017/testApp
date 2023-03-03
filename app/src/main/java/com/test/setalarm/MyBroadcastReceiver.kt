package com.test.setalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Vibrator
import android.util.Log
import android.widget.Toast


class MyBroadcastReceiver : BroadcastReceiver() {
    var mp: MediaPlayer? = null
    override fun onReceive(context: Context?, p1: Intent?) {
        /*mp=MediaPlayer.create(context, R.raw.alarm);
        mp.start();*/
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
        Log.d("ALARAM_NOT", "Stop-------------")
        val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(4000)

        /*var alarmUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }*/

        /*// setting default ringtone

        // setting default ringtone
        val ringtone = RingtoneManager.getRingtone(context, alarmUri)

        // play ringtone

        // play ringtone
        ringtone.play()*/


        val startIntent = Intent(context, RingtonePlayingService::class.java)
        //startIntent.putExtra("ringtone-uri", alarmUri)
        context.startService(startIntent)
    }

}
