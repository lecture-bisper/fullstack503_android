package bitc.fullstack.app_20250313

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250313.databinding.ActivityMp3Binding

class MP3Activity : AppCompatActivity() {

  private lateinit var binding: ActivityMp3Binding
//  MP3Service 객체 변수 선언, 지연 초기화 사용
  private lateinit var mp3PlayerService: MP3Service
//  ServiceConnection 객체 변수 선언, 지연 초기화 사용
  private val mp3PlayerServiceConnection by lazy {
    val connection = object : ServiceConnection {
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mp3PlayerService = (service as MP3Service.MP3Binder).getService()
      }

      override fun onServiceDisconnected(name: ComponentName?) {

      }
    }

    connection
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityMp3Binding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    MP3Service 용 Intent 생성
    val mp3Intent = Intent(this, MP3Service::class.java)
//    bindService()로 서비스 시작
    bindService(mp3Intent, mp3PlayerServiceConnection, Context.BIND_AUTO_CREATE)

//    이벤트 리스너 설정 함수
    initEventListener()
  }

  private fun initEventListener() {
    binding.btnPlay.setOnClickListener {
      mp3PlayerService.play()
    }

    binding.btnPause.setOnClickListener {
      mp3PlayerService.pause()
    }

    binding.btnStop.setOnClickListener {
      mp3PlayerService.stop()
    }
  }
}









