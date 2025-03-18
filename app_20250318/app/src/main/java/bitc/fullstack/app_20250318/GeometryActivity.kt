package bitc.fullstack.app_20250318

import android.Manifest
import android.content.pm.PackageManager
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250318.databinding.ActivityGeometryBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GeometryActivity : AppCompatActivity() {

  private val binding: ActivityGeometryBinding by lazy {
    ActivityGeometryBinding.inflate(layoutInflater)
  }

//  플랫폼 API 에서 제공하는 위치정보를 제어하는 서비스 객체
  private lateinit var locationManager: LocationManager

  private lateinit var fusedLocationClient: FusedLocationProviderClient

//  GPS 좌표가 변경되면 자동으로 동작하도록 하는 이벤트 리스너 생성
  private val listener: LocationListener = object : LocationListener {
//    GPS 좌표가 변경이 자동 실행
    override fun onLocationChanged(location: Location) {
      Log.d("fullstack503", "플랫폼 API 위치(Listener) -> 위도 : ${location.latitude}, 경도 : ${location.longitude}, 정확도 : ${location.accuracy}, 시간 : ${location.time}")
    }
  }

//  앱 사용 권한 확인
  private val locationPermissionRequest = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
  ) { permissions ->
    if (permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true
      || permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
      Log.d("fullstack503", "권한 허용됨")
    }
    else {
      Log.d("fullstack503", "권한 거부됨")
    }
  }

  @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    사용자에게 권한 획득 요청
    locationPermissionRequest.launch(
      arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
      )
    )

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

//    시스템 서비스를 사용하여 위치 정보 관리 객체를 생성
    locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
//    위치 정보 관리 객체에 이벤트 리스너 등록
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000L, 10f, listener)

    initEventListener()
  }

  private fun initEventListener() {

    binding.btnPlatformApi.setOnClickListener {
//      위치 정보를 가져오기 전에 권한 확인
      if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        Log.d("fullstack503", "권한이 없습니다.")
      }
      else {
//        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
//        전체 위치 정보자 출력
        val providers = locationManager.allProviders

        var result = ""
        for (provider in providers) {
          result += "$provider, "
        }

        Log.d("fullstack503", "전체 위치 제공자 : $result")

//        사용 가능한 위치 제공자 모두 출력
        val enableProviders = locationManager.getProviders(true)
        result = ""
        for (provider in enableProviders) {
          result += "$provider, "
        }

        Log.d("fullstack503", "사용 가능한 위치 제공자 : $result")

//        지정한 위치 제공자를 통해서 위치 정보를 1회 가져옴
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let {
          val latitude = it.latitude // 위도
          val longitude = it.longitude // 경도
          val accuracy = it.accuracy // 정확도
          val time = it.time // 시간

          Log.d("fullstack503", "플랫폼 API 위치 -> 위도 : $latitude, 경도 : $longitude, 정확도 : $accuracy,, 시간 : $time")
          Toast.makeText(this, "플랫폼 API 위치 -> 위도 : $latitude, 경도 : $longitude, 정확도 : $accuracy,, 시간 : $time", Toast.LENGTH_SHORT).show()
        } ?: Log.d("fullstack503", "플랫폼 API 위치 -> 정보 없음")
      }
    }

    binding.btnGooglePlayLib.setOnClickListener {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
        Log.d("fullstack503", "권한이 없습니다.")
      }
      else {
        fusedLocationClient.lastLocation
          .addOnSuccessListener { location: Location? ->
          location?.let {
            val latitude = it.latitude
            val longitude = it.longitude
            val accuracy = it.accuracy
            val time = it.time

            Log.d("fullstack503", "Google Play 라이브러리 위치 -> 위도 : $latitude, 경도 : $longitude, 정확도 : $accuracy, 시간 : $time")
            Toast.makeText(this, "Google Play 라이브러리 위치 -> 위도 : $latitude, 경도 : $longitude, 정확도 : $accuracy, 시간 : $time", Toast.LENGTH_SHORT).show()
          } ?: Log.d("fullstack503", "Google Play 라이브러리 위치 정보 없음")
        }
          .addOnFailureListener {
            Log.e("fullstack503", "Google Play 라이브러리 위치 정ㅇ보 가져오기 실패", it)
          }
      }
    }
  }

}











