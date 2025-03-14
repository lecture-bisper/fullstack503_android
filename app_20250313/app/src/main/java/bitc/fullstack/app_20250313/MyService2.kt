package bitc.fullstack.app_20250313

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

class MyService2 : Service() {

  lateinit var messenger: Messenger

//  내장 클래스 선언
//  첫번째 매개변수로 Context 타입의 데이터를 받음 (앱의 현재 정보 받아오기)
//  두번째 매개변수로 Context 타입의 데이터를 받음, val 키워드를 사용하여 필드로 선언, 기본값 설정
  internal class IncomingHandler(context: Context, private val applicationContext: Context = context.applicationContext) : Handler(Looper.getMainLooper()) {

//    send() 메소드 실행 시 자동 동작하는 메소드
    override fun handleMessage(msg: Message) {

//      전달받은 데이터를 키를 사용하여 확인
      when (msg.what) {
        10 -> Log.d("fullstack503", "${msg.obj}")
        20 -> Log.d("fullstack503", "${msg.obj}")
        else -> super.handleMessage(msg)
      }
    }
  }

  override fun onBind(intent: Intent): IBinder {
    messenger = Messenger(IncomingHandler(this))
    return messenger.binder
  }
}