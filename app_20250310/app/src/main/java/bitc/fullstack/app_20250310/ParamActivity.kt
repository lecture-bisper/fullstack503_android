package bitc.fullstack.app_20250310

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250310.databinding.ActivityParamBinding

class ParamActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityParamBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }


    Log.d("fullstack503", "ParamActivity 화면")

//    getXXXExtra() : 현재 엑티비티를 호출한 엑티비티에서 전달한 데이터를 가져오는 메소드
//    모든 데이터 타입 형식의 메소드가 존재함 (getIntExtra(), getDoubleExtra(), getStringExtra() )
//    getIntExtra() 나 getDoubleExtra() 는 두번째 매개변수를 사용하여 기본값을 설정할 수 있음
    val data1 = intent.getStringExtra("data1")
    val data2 = intent.getIntExtra("data2", 0)

    Log.d("fullstack503", "MainActivity 에서 전달받은 데이터 - data1 : $data1, data2 : $data2")

    binding.btnClose.setOnClickListener {
//      현재 엑티비티를 종료
      finish()
    }
  }
}