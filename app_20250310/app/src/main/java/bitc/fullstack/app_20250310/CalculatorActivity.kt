package bitc.fullstack.app_20250310

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250310.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityCalculatorBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("fullstack503", "CalculatorActivity 화면")

    val calIntent = intent

//    전달받은 데이터를 가져오기
    var num1 = calIntent.getIntExtra("num1", 0)
    var num2 = calIntent.getIntExtra("num2", 0)

//    EditText 에 전달받은 데이터를 입력
    binding.edtNum1.setText(num1.toString())
    binding.edtNum2.setText(num2.toString())

    binding.btnResult1.setOnClickListener {
      val strNum1 = binding.edtNum1.text.toString()
      val strNum2 = binding.edtNum2.text.toString()

      num1 = strNum1.toInt()
      num2 = strNum2.toInt()
      val result = num1 + num2

//      다시 되돌려줄 데이터를 저장할 Intent 생성
      val resultIntent = Intent().apply {
//        연산된 결과를 저장
        putExtra("result", result)
      }

//      현재 엑티비티 종료 후 복귀하는 엑티비티에 정상 종료라는 것을 알려주기 위한 값 설정
      setResult(Activity.RESULT_OK, resultIntent)
      finish()
    }

    binding.btnResult2.setOnClickListener {
      val strNum1 = binding.edtNum1.text.toString()
      val strNum2 = binding.edtNum2.text.toString()

      num1 = strNum1.toInt()
      num2 = strNum2.toInt()
      val result = num1 + num2

      val resultIntent = Intent().apply {
        putExtra("result", result)
      }
      setResult(Activity.RESULT_OK, resultIntent)
      finish()
    }
  }
}











