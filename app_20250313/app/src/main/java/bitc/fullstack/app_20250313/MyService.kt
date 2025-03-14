package bitc.fullstack.app_20250313

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

//  bindService()로 실행하는 서비스
class MyService : Service() {

  lateinit var myBinder: MyBinder

  override fun onCreate() {
    super.onCreate()

    Log.d("fullstack503", "*** bindService() 실행 - onCreate() 호출 ***")
    myBinder = MyBinder("MyService에서 생성한 MyBinder 객체")
  }

  override fun onBind(intent: Intent): IBinder {
    Log.d("fullstack503", "*** bindService() 실행 - onBind() 호출 ***")

    Log.d("fullstack503", "onBind() 안에서 데이터 확인 : ${myBinder.msg}")

    return myBinder
  }

  override fun onUnbind(intent: Intent?): Boolean {
    Log.d("fullstack503", "*** bindService() 실행 - onUnbind() 호출 ***")

    return super.onUnbind(intent)
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d("fullstack503", "*** bindService() 실행 - onDestroy() 호출 ***")
  }

}