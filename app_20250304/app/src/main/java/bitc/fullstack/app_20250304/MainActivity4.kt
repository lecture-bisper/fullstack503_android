package bitc.fullstack.app_20250304

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility

class MainActivity4 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
//    setContentView() : layout 파일을 현재 코틀린 소스파일과 연동하는 함수
    setContentView(R.layout.activity_main4)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    findViewById() : 지정한 layout 파일에 등록된 View 태그를 검색하여 가져오는 함수
//    해당 태그의 id 속성값을 기준으로 검색함
//    R.id.id명 : R은 리소스를 의미하고, id 는 View의 id 속성을 의미함
    
//    변수 btn01을 Button 클래스 타입으로 선언, findViewById()를 사용하여 id값이 btn01인 태그를 검색하여 변수 btn01에 저장
    val btn01: Button = findViewById(R.id.btn01)
    btn01.text = "소스코드에서 text를 변경한 btn01"

//    변수 btn02를 선언하고, findViewById()를 제네릭으로 Button 타입으로 지정 후 id값이 btn02인 태그를 검색하여 변수 btn02에 저장
    val btn02 = findViewById<Button>(R.id.btn02)
    btn02.text = "소스코드에서 text를 변경한 btn02"

    val btn03 = findViewById<Button>(R.id.btn03)

    btn01.setOnClickListener {
      btn03.visibility = View.VISIBLE
    }

    btn02.setOnClickListener {
      btn03.visibility = View.GONE
    }
  }
}






