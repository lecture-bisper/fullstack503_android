package bitc.fullstack.app_20250304

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250304.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//  요즘 안드로이드 폰의 디자인이 화면 및 카메라가 펀치홀 형태로 되어 있기 때문에 해당 위치를 제외하고 앱의 컨텐츠를 출력하는 함수
    enableEdgeToEdge()
    
//    뷰 바인딩 : 기존의 findViewById()를 통한 태그 검색 방식은 필요한 태그를 매번 findViewById()를 사용하여 검색하는 방식이었으나 뷰 바인딩 방식을 사용하면 한번에 모든 태그를 검색하여 가져오기 때문에 태그를 계속 검색할 필요가 없음
//    설정 : build.gradle.kts 파일의 android 설정안에 viewBinding { enable = true } 를 추가하여 사용
//    코드 부분에서 지정한 레이아웃을 불러와서 한번에 바인딩
    
//    ActivityMainBinding : 뷰 바인딩 방식으로 로딩된 xml 파일을 의미함
//    xml 파일의 형식을 Acitivity 를 접두사로 사용
//    중간 이름을 사용자가 입력한 이름으로 사용
//    접미사로 Binding 으로 구성되어 있음
    
//    inflate() : 실제로 바인딩을 진행하는 메소드
//    layoutInflater : 지정한 엑티비티 레이아웃 파일의 View 클래스 목록
    val binding = ActivityMain5Binding.inflate(layoutInflater)

//    setContentView(R.layout.activity_main5)
//    binding.root : 위에서 바인딩한 내용의 최상의 태그를 의미
    setContentView(binding.root)

//    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//    시스템 스테이터스바의 공간을 확인하고 해당 부분을 제외하고 앱의 컨텐츠를 출력하기 위한 설정
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    뷰 바인딩 사용 시 binding.id 값 형태로 사용함
//    binding.btn1
  }
}