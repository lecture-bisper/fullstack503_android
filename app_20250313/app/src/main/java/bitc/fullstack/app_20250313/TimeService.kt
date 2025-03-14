package bitc.fullstack.app_20250313

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TimeService : Service() {

//  onBind() 는 Service() 컴포넌트를 상속받아 사용 시 무조건 구현해야 함
  override fun onBind(intent: Intent): IBinder {
    Log.d("fullstack503", "### startService() 실행 - onBind() 호출 ###")

//  IBinder 를 구현한 클래스를 상속받아 객체를 생성하고 반환
    return TimeBinder()
  }

//  Service 컴포넌트의 생명주기 함수 중 객체 생성 시 1번만 실행됨
  override fun onCreate() {
    super.onCreate()

    Log.d("fullstack503", "### startService() 실행 - onCreate() 호출 ###")
  }

//  startService() 실행 시 매번 실행되는 생명주기 함수
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Log.d("fullstack503", "### startService() 실행 - onStartCommand() 호출 ###")

    return super.onStartCommand(intent, flags, startId)
  }

//  stopService() 실행 시 실행되는 함수, 서비스 종료
  override fun onDestroy() {
    super.onDestroy()

    Log.d("fullstack503", "### startService() 실행 - onDestroy() 호출 ###")
  }
}