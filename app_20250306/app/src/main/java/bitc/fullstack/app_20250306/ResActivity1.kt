package bitc.fullstack.app_20250306

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowMetrics
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250306.databinding.ActivityRes1Binding

class ResActivity1 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityRes1Binding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    코드에서 리소스에 접근 시 R 클래스를 사용하여 접근함
//    R.string 은 문자열 리소스(사용자 리소스인 strings.xml 및 안드로이드 시스템 리소스)를 의미함
    binding.tv03.text = getString(R.string.txt_data3)

    binding.tv04.text = getString(R.string.txt_data3)
//    코드에서 리소스에 등록된 색상을 적용하기
    binding.tv04.setTextColor(ResourcesCompat.getColor(resources, R.color.txt_color, null))
//    코드에서 리소스에 등록된 크기를 적용하기
    binding.tv04.textSize = resources.getDimension(R.dimen.txt_size)

//    코드에서 안드로이드 기본 시스템 제공 리소스 사용하기
    binding.tv08.text = getString(android.R.string.emptyPhoneNumber)


//    현재 안드로이드 기기의 버전이 지정한 안드로이드 SDK 버전보다 같거나 높을 경우 신규 방식을 실행
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
      binding.tv09.text = "width : ${windowMetrics.bounds.width()}, height : ${windowMetrics.bounds.height()}"
    }
//    현재 안드로이드 기기의 버전이 지정한 안드로이드 SDK 버전보다 낮으면 예전 방식으로 실행
    else {
      val display = windowManager.defaultDisplay
      val displayMetrics = DisplayMetrics()
      display?.getRealMetrics(displayMetrics)
      binding.tv09.text = "width : ${displayMetrics.widthPixels}, height : ${displayMetrics.heightPixels}"
    }
  }
}







