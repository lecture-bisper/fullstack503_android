package bitc.fullstack.app_20250312

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250312.databinding.ActivityReceiverBinding
import bitc.fullstack.app_20250312.databinding.ActivityRecyclerViewBinding

class ReceiverActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityReceiverBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    권한 확인
//    RequestMultiplePermissions() 메소드를 콜백함수로 등록하여 권한 획득 다이얼로그창 출력
//    사용자에게 권한 획득을 요청함
    val permissionLauncher = registerForActivityResult( ActivityResultContracts.RequestMultiplePermissions() ) {
      if (it.all { permission -> permission.value == true }) {
//        권한 획득 시 브로드 캐스트 리시버용 인텐트 생성 및 시스템으로 전달
        val intent = Intent(this, MyReceiver::class.java)
        sendBroadcast(intent)
      }
      else {
//        권한이 없을 경우 토스트 메시지 출력
        Toast.makeText(this, "권한 없음", Toast.LENGTH_SHORT).show()
      }
    }

//    사용자 브로드캐스트 리시버 동적 등록
//    베터리 충전 정보를 사용
    registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!.apply {
//      베터리 상태를 확인
      when (getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
//        베터리 충전 상태 확인 (충전 중인지 아닌지 확인)
        BatteryManager.BATTERY_STATUS_CHARGING -> {
//        베터리가 충전 중일때 USB 충전인지 AC 전원 충전인지 확인
          when (getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
            BatteryManager.BATTERY_PLUGGED_USB -> {
              binding.tvChargingResult.text = "USB 충전 중"
              binding.ivCharging.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.usb))
            }

            BatteryManager.BATTERY_PLUGGED_AC -> {
              binding.tvChargingResult.text = "AC 충전 중"
              binding.ivCharging.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.ac))
            }
          }
        }

        else -> {
          binding.tvChargingResult.text = "충전 중이 아닙니다."
        }
      }

      val level = getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
      val scale = getIntExtra(BatteryManager.EXTRA_SCALE, -1)
      val batteryPct = level / scale.toFloat() * 100
      binding.tvPercentResult.text = "$batteryPct"
    }

    binding.btnRunReceiver.setOnClickListener {
//      사용하는 안드로이드 버전이 API 33 이상일 경우 
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        
//        지정한 권한이 있는지 여부 가져오기
        val isGrant = ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS")

//        가져온 권한 정보에서 권한이 있는지 아닌지 확인
        if (isGrant == PackageManager.PERMISSION_GRANTED) {
//          권한이 있을 경우, 브로드캐스트 리시버용 인텐트 생성
          val intent = Intent(this, MyReceiver::class.java)
//          안드로이드 시스템으로 브로드캐스트 리시버 전달
          sendBroadcast(intent)
        }
        else {
//          권한이 없을 경우, 권한 획득 메시지창 출력
          permissionLauncher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
        }
      }
//      API 33 미만일 경우
      else {
//        권한 확인없이 브로드캐스트 리시버용 인텐트 생성 및 안드로이드 시스템으로 전달
        val intent = Intent(this, MyReceiver::class.java)
        sendBroadcast(intent)
      }
    }
  }
}










