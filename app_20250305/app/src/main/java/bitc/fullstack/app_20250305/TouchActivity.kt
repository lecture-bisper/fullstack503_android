package bitc.fullstack.app_20250305

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250305.databinding.ActivityTouchBinding

class TouchActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityTouchBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    API 33 이후부터 뒤로가기 버튼을 제어하기 위한 방법
    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        Log.d("fullstack503", "뒤로 가기 버튼 다른 방식으로 사용하기")
        Toast.makeText(this@TouchActivity, "뒤로 가기 버튼 클릭!!", Toast.LENGTH_SHORT).show()
      }
    })
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {

    when (event?.action) {
      MotionEvent.ACTION_DOWN -> {
        Log.d("fullstack503", "터치 다운 이벤트 발생")
        Log.d("fullstack503", "터치 위치 - x : ${event.x}, y : ${event.y}, rawX : ${event.rawX}, rawY : ${event.rawY}")
      }
      MotionEvent.ACTION_UP -> {
        Log.d("fullstack503", "터치 업 이벤트 발생")
      }
      MotionEvent.ACTION_MOVE -> {
        Log.d("fullstack503", "터치 위치 - x : ${event.x}, y : ${event.y}, rawX : ${event.rawX}, rawY : ${event.rawY}")
      }
    }

    return super.onTouchEvent(event)
  }


//  키 다운/업 이벤트는 전원키, 오버뷰키, 홈키 는 이벤트 동작이 안됨(시스템 키 이기 때문에 앱에서 제어 안됨)
//  뒤로가기 키는 키 이벤트로 제어 가능함
//  뒤로가기 키는 onBackPressed() 함수를 사용하여 제어 가능함(API 33 부터 onBackPressed() 가 제거됨)
  override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

    Log.d("fullstack5503", "키 다운 이벤트 발생")

    when (keyCode) {

      KeyEvent.KEYCODE_0 -> Log.d("fullstack503", "0 키를 눌렀습니다.")
      KeyEvent.KEYCODE_A -> Log.d("fullstack503", "A 키를 눌렀습니다.")
      KeyEvent.KEYCODE_VOLUME_UP -> Log.d("fullstack503", "볼륨 업 버튼을 눌렀습니다.")
      KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("fullstack503", "볼륨 다운 버튼을 눌렀습니다.")
//      뒤로 가기 버튼 실행 시 뒤로가기 버튼의 기본 동작이 동작하고 사용자가 추가한 내용도 실행됨
//      KeyEvent.KEYCODE_BACK -> Log.d("fullstack503", "뒤로가기 버튼을 눌렀습니다.")
    }

    return super.onKeyDown(keyCode, event)
  }

  override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

    Log.d("fullstack503", "키 업 이벤트 발생")

    return super.onKeyUp(keyCode, event)
  }

}












