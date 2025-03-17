package bitc.fullstack.app_202550317

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.SubscriptionManager
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_202550317.databinding.ActivityCallStateBinding

class CallStateActivity : AppCompatActivity() {

  private val binding: ActivityCallStateBinding by lazy {
    ActivityCallStateBinding.inflate(layoutInflater)
  }

//  통화 상태 이벤트 감지를 위한 변수
  private var telephonyManager: TelephonyManager? = null

//  전화번호를 가져오기 위한 변수
  private var subscriptionManager: SubscriptionManager? = null
  
//  네트워크 접속 정보를 가져오기 위한 변수
  private var connectivityManager: ConnectivityManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    initEventListener()
  }

  @SuppressLint("HardwareIds")
  private fun initEventListener() {

    binding.btnCallStatus.setOnClickListener {
      if (telephonyManager == null) {
//        통화 상태 정보 가져오기
//        안드로이드 시스템의 서비스 중 TelephonyManager 타입의 객체를 가져옴
        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

//        API 31 (안드로이드12) 이상부터 사용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//          TelephonyManager 객체에 콜백 메소드 등록
          telephonyManager?.registerTelephonyCallback(
//            쓰레드로 동작하도록 설정
            mainExecutor,
//            TelephonyCallback() 클래스를 상속받아 구현함
            object : TelephonyCallback(), TelephonyCallback.CallStateListener {
//              통화 상태 정보가 변경 시 자동으로 동작
              override fun onCallStateChanged(state: Int) {
                when (state) {
                  TelephonyManager.CALL_STATE_IDLE -> Log.d("fullstack503", "통화 대기 중 (IDLE)")
                  TelephonyManager.CALL_STATE_OFFHOOK -> Log.d("fullstack503", "통화 종료 음 출력 (CALL_STATE_OFFHOOK)")
                  TelephonyManager.CALL_STATE_RINGING -> Log.d("fullstack503", "통화 연결 신호음 출력 (CALL_STATE_RINGRING)")
                }
              }
            }
          )
        }
        else {
          val phoneStateListener = object : PhoneStateListener() {

            override fun onCallStateChanged(state: Int, phoneNumber: String?) {
              super.onCallStateChanged(state, phoneNumber)

              when (state) {
                TelephonyManager.CALL_STATE_IDLE -> Log.d("fullstack503", "통화 대기 중 (IDLE)")
                TelephonyManager.CALL_STATE_OFFHOOK -> Log.d("fullstack503", "통화 종료 음 출력 (CALL_STATE_OFFHOOK)")
                TelephonyManager.CALL_STATE_RINGING -> Log.d("fullstack503", "통화 연결 신호음 출력 (CALL_STATE_RINGRING)")
              }
            }
          }

          telephonyManager?.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
        }

        Toast.makeText(this, "통화 상태 이벤트를 시작합니다.", Toast.LENGTH_SHORT).show()
      }
      else {
        Toast.makeText(this, "통화 상태 확인 이벤트가 이미 동작 중입니다.", Toast.LENGTH_SHORT).show()
      }
    }

    binding.btnPhoneNumber.setOnClickListener {
      var phoneNumber: String = ""

      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED) {
        Log.d("fullstack503", "사용 권한이 없습니다.")
      }
      else {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

          subscriptionManager = getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

          val subscriptionInfoList = subscriptionManager!!.activeSubscriptionInfoList

          if (!subscriptionInfoList.isNullOrEmpty()) {

            phoneNumber = subscriptionInfoList[0].number
            Log.d("fullstack503", "가져온 전화번호 : $phoneNumber")
            Toast.makeText(this, "가져온 전화번호 : $phoneNumber", Toast.LENGTH_SHORT).show()
          }
          else {
            Log.d("fullstack503", "등록된 SIM 정보가 없습니다.")
            Toast.makeText(this, "등록된 SIM 정보가 없습니다.", Toast.LENGTH_SHORT).show()
          }
        }
        else {
          if (telephonyManager == null) {
            telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            phoneNumber = telephonyManager!!.line1Number
            Log.d("fullstack503", "가져온 전화번호 : $phoneNumber")
            Toast.makeText(this, "가져온 전화번호 : $phoneNumber", Toast.LENGTH_SHORT).show()
          }
          else {
            Log.d("fullstack503", "등록된 SIM 정보가 없습니다")
            Toast.makeText(this, "등록된 SIM 정보가 없습니다.", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }

    binding.btnNetworkState.setOnClickListener {

      if (connectivityManager == null) {
//        안드로이드 시스팀의 CONNECTIVITY_SERVICE로 ConnectivityManager 객체 가져오기
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
      }

//      네트워크 상태 결과 가져오기
      val result: MutableMap<String, Any> = isNetworkAvailable()

      if (result["result"].toString().toBoolean()) {
        Log.d("fullstack503", "네트워크 접속 가능${" - " + result["value"]}")
        Toast.makeText(this, "네트워크 접속 가능${" - " + result["value"]}", Toast.LENGTH_SHORT).show()
      }
      else {
        Log.d("fullstack503", "네트워크 접속 불가")
        Toast.makeText(this, "네트워크 접속 불가", Toast.LENGTH_SHORT).show()
      }
    }
  }

//  네트워크 상태를 확인하고 결과를 MutableMap<String, Any> 타입으로 반환
  private fun isNetworkAvailable(): MutableMap<String, Any> {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//      현재 네트워크 상태 확인
      val nw = connectivityManager?.activeNetwork
//      사용하고 있는 네트워크 종류 가져오기, 사용하고 있는 네트워크가 없을 경우 바로 false를 전달
      val actNw = connectivityManager?.getNetworkCapabilities(nw) ?: return mutableMapOf("result" to false)

//      제어문으로 가져온 네트워크의 종류 확인
      return when {
//        WIFI
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
          Log.d("fullstack503", "wifi 네트워크 사용")
          mutableMapOf("result" to true, "value" to "WIFI 네트워크 사용")
        }
//        CELLULAR
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
          Log.d("fullstack503", "cellular 네트워크 사용")
          mutableMapOf("result" to true, "value" to "CELLULAR 네트워크 사용")
        }
        else -> mutableMapOf("result" to false)
      }
    }
    else {
//      옛날방식, 네트워크에 접속 가능한지만 확인
      val isConnection = connectivityManager?.activeNetworkInfo?.isConnected ?: false
      return if (isConnection) {
        mutableMapOf("result" to true, "value" to "")
      }
      else {
        mutableMapOf("result" to false)
      }
    }
  }
}










