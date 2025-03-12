package bitc.fullstack.app_20250312

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MyReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {

    Log.d("fullstack503", "MyReceiver.....")

    val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
    val builder: NotificationCompat.Builder

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channelId = "channel-one"
      val channelName = "My Channel-one"
      val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
        description = "채널 설명"
        setShowBadge(true)
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()

        setSound(uri, audioAttributes)
        enableVibration(true)
      }

      manager.createNotificationChannel(channel)
      builder = NotificationCompat.Builder(context, channelId)
    }
    else {
      builder = NotificationCompat.Builder(context)
    }

    builder.run {
      setSmallIcon(android.R.drawable.ic_notification_overlay)
      setWhen(System.currentTimeMillis())
      setContentTitle("홍길동")
      setContentText("안녕하세요")
    }

    manager.notify(11, builder.build())
  }
}







