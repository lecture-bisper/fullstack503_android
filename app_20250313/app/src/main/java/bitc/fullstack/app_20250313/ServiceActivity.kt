package bitc.fullstack.app_20250313

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250313.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

  private lateinit var startServiceIntent: Intent

  private lateinit var serviceBinder: MyBinder
  private lateinit var connection: ServiceConnection
//  안드로이드 API에서 제공하는 IBinder 를 상속받아 구현한 Messenger 클래스
  private lateinit var messenger: Messenger
  private lateinit var connection2: ServiceConnection

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityServiceBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    connection = object: ServiceConnection {
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d("fullstack503", "*** onServiceConnected 실행됨 ***")

        serviceBinder = service as MyBinder
        Log.d("fullstack503", "onServiceConnected() 에서 데이터 확인 : ${serviceBinder.msg}")
      }

      override fun onServiceDisconnected(name: ComponentName?) {
        Log.d("fullstack503", "*** onServiceDisconnected 실행됨 ***")
      }
    }

    connection2 = object: ServiceConnection {
//      onBind() 실행 시 자동으로 실행되는 메소드
//      두번째 매개변수로 IBinder 타입의 객체가 전달됨
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//        전달받은 IBinder 타입의 객체를 사용하여 Messenger 객체 생성
        messenger = Messenger(service)
      }

      override fun onServiceDisconnected(name: ComponentName?) {
      }
    }



//    startService()로 서비스 시작
    binding.btnStartService.setOnClickListener {
      Log.d("fullstack503", "서비스 시작 버튼 클릭!!")

      startServiceIntent = Intent(this, TimeService::class.java)
      startService(startServiceIntent)
    }

    //    stopService()로 서비스 종료
    binding.btnStopService.setOnClickListener {
      Log.d("fullstack503", "서비스 종료 버튼 클릭!!")

      stopService(startServiceIntent)
    }

//    bindService()로 서비스 시작
    binding.btnBindService.setOnClickListener {
      val intent = Intent(this, MyService::class.java)
      bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

//    unbindService()로 서비스 종료
    binding.btnUnbindService.setOnClickListener {
      unbindService(connection)
    }

    binding.btnBindService2.setOnClickListener {
      val intent = Intent(this, MyService2::class.java)
      bindService(intent, connection2, Context.BIND_AUTO_CREATE)
    }

    binding.btnUnbindService2.setOnClickListener {
//      Message : 안드로이드 API 가 제공하는 데이터 전달용 클래스
      val msg = Message()
//      데이터의 키
      msg.what = 10
//      데이터의 값
      msg.obj = "안녕하세요"
      messenger.send(msg)
    }
  }
}









