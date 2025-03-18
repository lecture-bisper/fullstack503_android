package bitc.fullstack.app_20250318

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250318.databinding.ActivityNaverMapBinding
import com.naver.maps.map.NaverMapSdk

class NaverMapActivity : AppCompatActivity() {

  private val binding: ActivityNaverMapBinding by lazy {
    ActivityNaverMapBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("wyaioa282l")
  }
}