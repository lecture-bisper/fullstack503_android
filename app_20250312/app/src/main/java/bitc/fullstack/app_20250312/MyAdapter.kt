package bitc.fullstack.app_20250312

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bitc.fullstack.app_20250312.databinding.ItemRecyclerViewBinding


//  xml 파일을 파싱한 ViewHolder 를 여러개 가지는 Adapter 클래스
//  매개변수로 리사이클러 뷰에 표시할 데이터를 받아옴
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//  ViewHolder 가 생성될 때 실행됨, 전체 UI 를 생성함
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MyViewHolder(ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

//  매개변수로 받아온 데이터의 수 반환, 매개변수의 크기에 따라 ViewHolder가 생성됨
  override fun getItemCount(): Int {
    return datas.size
  }

//  생성된 리사이클러 뷰의 ViewHolder 에 데이터를 연동
//  첫번째 매개변수로 RecyclerView.ViewHolder 타입의 객체를 가져옴, RecyclerView.ViewHolder 의 객체 안에 들어있는 것은 자식 클래스 타입의 객체인 MyViewHolder 임
//  두번째 매개변수는 MyAdapter 클래스 타입의 객체를 생성 시 매개변수로 받아온 데이터의 index 번호
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    Log.d("fullstack503", "onBindViewHolder : $position")

//  자식 클래스 타입의 객체로 강제 타입 변환, 자식 클래스 타입의 객체가 가지고 있던 태그 정보를 변수에 저장
    val binding = (holder as MyViewHolder).binding

//  itemData 는 강제 타입 변환된 객체가 item_recycler_view.xml 파일에 있는 태그의 id 임
//  xml 파일의 태그를 선택하여 필요한 데이터를 화면에 설정함
    binding.itemData.text = datas[position]

//  지정한 태그에 클릭 이벤트 설정
    binding.itemData.setOnClickListener {
      Log.d("fullstack503", "item data click : $position")
    }
  }

//  기존 데이터에 새로운 데이터를 추가
  public fun addItem(data: String) {
    datas.add(data)
//  추가된 데이터 적용
    notifyItemInserted(datas.size - 1)
  }
}














