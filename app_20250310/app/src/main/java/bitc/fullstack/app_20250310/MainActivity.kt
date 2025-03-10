package bitc.fullstack.app_20250310

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250310.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//  ActivityResultLauncher 를 사용하여 데이터를 돌려받을 때 사용하는 콜백 함수
  private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    data ->
    if (data.resultCode == Activity.RESULT_OK) {
      val result = data.data?.getIntExtra("result", 0)
      Log.d("fullstack503", "ActivityResultLauncher 로 돌려받은 데이터 - result : $result")
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("fullstack503", "MainActivity 화면")

//    버튼 이벤트
    binding.btnMove1.setOnClickListener {
      Log.d("fullstack503", "MainActivity 에서 DetailActivity 로 이동")

//      Intent를 사용하여 시스템에 지정한 엑티비티로 이동하도록 요청 설정
      val intent = Intent(this, DetailActivity::class.java)
//      startActivity() 를 사용하여 시스템에 intent를 전달
      startActivity(intent)
    }

    binding.btnMove2.setOnClickListener {
      Log.d("fullstack503", "MainActivity 에서 ParamActivity 로 이동")

      val intent = Intent(this, ParamActivity::class.java)
//      putExtra() : 지정한 엑티비티로 데이터를 전달할 때 사용하는 메소드
//      첫번째 매개변수 : 전달하는 데이터의 키명
//      두번재 매개변수 : 전달되는 실제 데이터
      intent.putExtra("data1", "첫번째 데이터")
      intent.putExtra("data2", 100)

//      Intent로 설정한 엑티비티로 화면 전환하는 명령어
//      startActivity() : 단순 엑티비티 전환 명령어
//      startActivityForResult() : 지정한 엑티비티로 화면 전환 후 전환된 엑티비티에서 데이터를 돌려받는 명령어, 예전 버전용
//      ActivityResultLauncher : 지정한 엑티비티로 화면 전환 후 전환된 엑티비티에서 데이터를 돌려받는 명령어, 최신 버전용
      startActivity(intent)
    }

//    startActivityForResult() 로 데이터를 돌려받는 버튼 이벤트
    binding.btnMove3.setOnClickListener { 
      val intent = Intent(this, CalculatorActivity::class.java)

      intent.putExtra("num1", 100)
      intent.putExtra("num2", 200)

      startActivityForResult(intent, 1000)
    }
    
//    ActivityResultLauncher 로 데이터를 돌려받는 버튼 이벤트
    binding.btnMove4.setOnClickListener { 
      val intent = Intent(this, CalculatorActivity::class.java)

      intent.putExtra("num1", 100)
      intent.putExtra("num2", 200)

      launcher.launch(intent)
    }
  }
  
  
//  startActivityForResult() 실행 후 지정한 엑티비티가 종료되면 자동 실행되는 콜백 함수, 이전 엑티비티가 전달하는 데이터를 가져옴
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode == Activity.RESULT_OK) {
      val result = data?.getIntExtra("result", 0)
      Log.d("fullstack503", "startActivityForResult() 로 돌려받은 데이터 - result : $result")
    }
  }
}











