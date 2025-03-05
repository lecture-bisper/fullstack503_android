package bitc.fullstack.app_20250305

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250305.databinding.ActivityViewEventBinding

//  1번 이벤트 방식 사용 시 사용할 이벤트 리스너를 상속받음
class ViewEventActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

  private val total = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityViewEventBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }



//    layout에 있는 지정한 태그에 이벤트 설정
    binding.cBox01.setOnCheckedChangeListener { buttonView, isChecked ->

      if (isChecked) {
        Log.d("fullstack503", "체크 박스 클릭, 체크 됨")
      }
      else {
        Log.d("fullstack503", "체크 박스 클릭, 체크 안됨")
      }
    }

//    이벤트 설정 방법이 3가지 존재함
//    1. 엑티비티 클래스에 지정한 View 클래스를 상속받고 해당 이벤트 메소드를 오버라이딩해서 사용
//    2. 해당 View 클래스를 상속받고 이벤트 메소드를 오버라이딩하는 클래스를 따로 생성해서 사용
//    3. 이벤트 리스너를 등록과 동시에 익명 객체를 생성하여 이벤트 메소드를 오버라이딩해서 사용
//        가장 사용하기 쉬움

//    1번 이벤트 방식을 사용하여 이벤트 소스(View 클래스 객체)에 이벤트 리스너를 연동
    binding.cBox02.setOnCheckedChangeListener(this)
    
//    2번 이벤트 방식을 사용하여 이벤트 소스에 이벤트 리스너를 연동
    binding.cBox03.setOnCheckedChangeListener(MyCheckBoxEventHandler())
    
//    3번 이벤트 방식을 사용하여 이벤트 소스에 이벤트 리스너를 익명 객체로 구현하여 연동
    binding.cBox04.setOnCheckedChangeListener { buttonView, isChecked ->

      if (isChecked) {
        Log.d("fullstack503", "3번 방식으로 체크 이벤트 발생, 체크!!")
      }
      else {
        Log.d("fullstack503", "3번 방식으로 체크 이벤트 발생, 체크 해제!!")
      }
    }

//    3번 이벤트 방식을 사용하여 클릭 이벤트 등록
    binding.btnEvent01.setOnClickListener {
      Log.d("fullstack503", "버튼 클릭 이벤트 발생!!")
//      Toast : 안드로이드에서 제공하는 알림 메시지 창
//      화면 하단에 잠시 출력되고 사라짐(사용자가 제어할 수 없음)
//      makeText() : 토스트 메시지에 출력할 텍스트와 토스트 메시지 출력 시간을 설정
//        첫번째 매개변수는 어디에 토스트 메시지를 출력할지 설정
//          this 는 현재 엑티비티 객체를 의미
//          this@엑티비티명 은 this가 가르키는 객체가 현재 엑티비티가 아닐 경우 사용함
//        두번째 매개변수는 출력할 텍스트
//        세번째 매개변수는 토스트 메시지 출력 시간, LENGTH_SHORT (3초)/LENGTH_LONG (5초)
//      show() : 설정된 토스트 메시지를 화면에 출력

      val value = binding.tvBoxCal.text
      Toast.makeText(this, "버튼 클릭 이벤트!! ${value}", Toast.LENGTH_SHORT).show()

    }

//    3번 이벤트 방식을 사용하여 롱 클릭 이벤트 등록
    binding.btnEvent01.setOnLongClickListener {
      Log.d("fullstack503", "버튼 롱 클릭 이벤트 발생!!")
      Toast.makeText(this, "버튼 롱 클릭 이벤트!!", Toast.LENGTH_SHORT).show()
      true
    }

  }

//  1번 이벤트 방식을 사용 시 실제 동작할 이벤트 핸들러
  override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

    if (isChecked) {
      Log.d("fullstack503", "1번 방식으로 체크 이벤트 발생, 체크!!")
    }
    else {
      Log.d("fullstack503", "1번 방식으로 체크 이벤트 발생, 체크 해제!!")
    }
  }
}

//  2번 이벤트 방식 사용 시 이벤트 리스너를 상속받은 클래스를 따로 생성하여 사용
class MyCheckBoxEventHandler : CompoundButton.OnCheckedChangeListener {
  
//  2번 이벤트 방식 사용 시 동작할 이벤트 핸들러, 따로 생성한 클래스의 멤버로 구성되어 있음
  override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

    this
    if (isChecked) {
      Log.d("fullstack503", "2번 방식으로 체크 이벤트 발생, 체크!!")
    }
    else {
      Log.d("fullstack503", "2번 방식으로 체크 이벤트 발생, 체크 해제!!")
    }
  }
}

//  문제 1) 책 231 페이지의 스톱워치 프로그램 작성하기
//  문제 2) 현재까지 배운 Layout 과 View 이벤트를 활용하여 계산기 프로그램을 작성하세요
//    UI 는 원하는대로 만들기
//    숫자를 누르기 위한 버튼(0 ~ 9)은 반드시 있어야 함

