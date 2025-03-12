package bitc.fullstack.app_20250311

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250311.databinding.ActivityToolbarBinding

class ToolbarActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityToolbarBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    setSupportActionBar(binding.toolbar)
    supportActionBar?.title = getString(R.string.app_name)
//    툴바의 뒤로가기 버튼 설정
//    뒤로가기 버튼 설정 방법은 2가지가 존재함
//    1. AndroidManifest 파일의 액티비티 부분에 parentActivityName 속성에 이동할 부모 액티비티명을 입력하고, 자식 액티비티의 코틀린 소스부분에 supportActionBar?.setDisplayHomeAsUpEnabled(true) 를 사용
//    2. 자식 액티비티의 코틀린 소스부분에 supportActionBar?.setDisplayHomeAsUpEnabled(true) 를 사용하고, onSupportNavigateUp() 메소드를 오버로딩하여 사용
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

//  2번 방식을 사용하여 툴바에 뒤로 가기 버튼을 활성화했을 경우 뒤로가기 기능을 추가하는 메소드
//  뒤로가기 버튼 클릭 시 추가로 작업하고 싶은 것이 있을 경우에도 사용할 수 있음
  override fun onSupportNavigateUp(): Boolean {
    super.onSupportNavigateUp()
    Log.d("fullstack503", "툴바의 뒤로가기 버튼 클릭!!")
    Toast.makeText(this, "뒤로가기 버튼 클릭", Toast.LENGTH_SHORT).show()
//  실제 뒤로 가기 버튼 기능을 실행
    onBackPressedDispatcher.onBackPressed()
    return true
  }

//  툴바에 메뉴를 등록하는 메소드
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    소스코드로 직접 메뉴 등록
//    val menuItem1: MenuItem? = menu?.add(0, 0, 0, "메뉴1")
//    val menuItem2: MenuItem? = menu?.add(0, 1, 0, "메뉴2")

//    xml 파일로 생성한 메뉴를 등록
    menuInflater.inflate(R.menu.menu_main, menu)

//    findItem(메뉴 리소스 ID) : 메뉴 xml 파일의 태그 중 지정한 id를 가지고 있는 태그를 검색하여 가져옴
    val menuItem1 = menu?.findItem(R.id.menu1)
    menuItem1?.setOnMenuItemClickListener {
      Log.d("fullstack503", "xml로 생성한 메뉴 1 클릭")
      Toast.makeText(this, "xml로 생성한 메뉴 1 클릭", Toast.LENGTH_SHORT).show()
      true
    }

    val menuItem2 = menu?.findItem(R.id.menu2)
    menuItem2?.setOnMenuItemClickListener {
      Log.d("fullstack503", "xml로 생성한 메뉴 2 클릭")
      Toast.makeText(this, "xml로 생성한 메뉴 2 클릭", Toast.LENGTH_SHORT).show()
      true
    }

//    SearchView 사용하기
//    findItem() 사용하여 메뉴 버튼을 검색
    val menuItem3 = menu?.findItem(R.id.menu3)
//    검색된 메뉴 버튼에서 SearchView 객체 가져오기
    val searchView = menuItem3?.actionView as SearchView
//    이벤트 등록
    searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//      텍스트 입력 후 확인 버튼을 눌렀을 때 동작, 이벤트 발생 후 현재까지 입력된 모든 텍스트를 매개변수로 받아옴
      override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("fullstack503", "입력한 내용 : $query")
        return true
      }
//      입력된 텍스트의 변경이 발생 시 동작, 현재까지 입력된 텍스트를 매개변수로 받아옴
      override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("fullstack503", "입력 중인 내용 : $newText")
        return true
      }
    })

    return super.onCreateOptionsMenu(menu)
  }

//  툴바의 오버플로 메뉴 이벤트 설정
//  override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//    when (item.itemId) {
//      0 -> {
//        Log.d("fullstack503", "첫번째 메뉴 클릭!!")
//        Toast.makeText(this, "첫번째 메뉴 클릭!!", Toast.LENGTH_SHORT).show()
//        return true
//      }
//      1 -> {
//        Log.d("fullstack503", "두번째 메뉴 클릭!!")
//        Toast.makeText(this, "두번째 메뉴 클릭!!", Toast.LENGTH_SHORT).show()
//        return true
//      }
//      else -> return super.onOptionsItemSelected(item)
//    }
//  }



}











