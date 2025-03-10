package bitc.fullstack.app_20250307

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ActionReceiver : BroadcastReceiver() {

//  첫번째 매개변수 : 현재 브로드캐스트 리시버를 동작시킨 앱 혹은 엑티비티
  override fun onReceive(context: Context?, intent: Intent?) {
    val manager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    when (intent?.action) {
      "OK" -> {
        Toast.makeText(context, "액션 확인 버튼 클릭", Toast.LENGTH_SHORT).show()
        Log.d("fullstack503", "액션 확인 버튼 클릭")
      }
      "CANCEL" -> {
        Toast.makeText(context, "액션 취소 버튼 클릭", Toast.LENGTH_SHORT).show()
        Log.d("fullstack503", "액션 취소 버튼 클릭")
      }
      "MORE" -> {
        Toast.makeText(context, "액션 자세히보기 버튼 클릭", Toast.LENGTH_SHORT).show()
        Log.d("fullstack503", "액션 자세히보기 버튼 클릭")
      }
    }

//  알림 제거, 해당 알림을 안드로이드 시스템에 등록 시 사용한 id 번호로 알림 제거
    manager.cancel(12)
  }
}














