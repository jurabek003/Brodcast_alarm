package uz.turgunboyevjurabek.brodcastalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val uri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL)
        val mediaPlayer=MediaPlayer()
        mediaPlayer.setDataSource(context, uri)
        mediaPlayer.prepare()
        mediaPlayer.start()
       // context.startActivity(Intent(context,MainActivity2::class.java))
    }
}