package bitc.fullstack.app_20250312

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import bitc.fullstack.app_20250312.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

  private val items = mutableListOf<String>()
  private lateinit var adapter: MyAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    리사이클러 뷰에 사용할 데이터 목록 생성 (데이터가 없어서 생성함)
//    items = mutableListOf<String>()
    for (i in 1..20) {
      items.add("Item $i")
    }

//    MyAdapter 클래스 타입의 객체 생성
    adapter = MyAdapter(items)
//    현재 엑티비티의 리사이클러 뷰에 사용하고자하는 layout 을 지정함
//    LinearLayoutManager : xml 파일에 layout을 설정하기 위해서 사용하는 LinearLayout 태그를 의미, 소스코드에서 실시간으로 생성해서 사용할 경우 사용하는 클래스
    binding.recyclerView.layoutManager = LinearLayoutManager(this)
//    현재 엑티비티의 리사이클러 뷰에 adapter 를 연동, MyAdapter 클래스 타입의 객체 사용, 생성자의 매개변수로 위에서 생성한 데이터를 전달
    binding.recyclerView.adapter = adapter
//    추가로 리사이클러 뷰의 모양을 설정 시 사용
    binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    binding.btnItemAdd.setOnClickListener {
//      책에 있는 데이터 추가 방식
//      items.add("Item ${items.size + 1}")
//      변경된 데이터를 모두 다시 적용
//      adapter.notifyDataSetChanged()
      val newItem = "Item ${items.size + 1}"
      adapter.addItem(newItem)
    }

  }
}









