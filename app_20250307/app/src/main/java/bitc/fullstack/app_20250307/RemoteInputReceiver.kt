package bitc.fullstack.app_20250307

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.RemoteInput

class RemoteInputReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

//    매개변수에 ? 가 없을 경우
    val remoteInput = RemoteInput.getResultsFromIntent(intent)
    val receiverText = remoteInput?.getCharSequence("fullstack503-receiver").toString()

    Toast.makeText(context, "전달받은 메시지 : $receiverText", Toast.LENGTH_SHORT).show()
    Log.d("fullstack503", "전달받은 메시지 : $receiverText")
    
//    매개변수에 ? 가 있을 경우, null 안정성을 위한 소스코드
//    val remoteInput = intent?.let { RemoteInput.getResultsFromIntent(it) }
//    val receiverText = remoteInput?.getCharSequence("fullstack503-receiver")?.toString()

//    if (!receiverText.isNullOrEmpty()) {
//      Toast.makeText(context, "전달받은 메시지 : $receiverText", Toast.LENGTH_SHORT).show()
//      Log.d("fullstack503", "전달받은 메시지 : $receiverText")
//    }

    manager.cancel(13)
  }
}







