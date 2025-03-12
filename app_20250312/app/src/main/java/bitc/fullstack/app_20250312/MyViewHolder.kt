package bitc.fullstack.app_20250312

import androidx.recyclerview.widget.RecyclerView
import bitc.fullstack.app_20250312.databinding.ItemRecyclerViewBinding

//  리사이클러 뷰의 개별 항목 xml 파일을 파싱하는 ViewHolder 파일
//  매개변수로 리사이클러 뷰의 개별 항목이 되는 xml 파일을 받아옴
//  주 생성자의 매개변수에 val 키워드를 사용하여 해당 클래스의 필드로 선언
//  원래는 findViewById() 함수로 xml 파일이 가지고 있는 모든 태그를 가져와서 부모 클래스의 생성자에 전달하는 방식을 사용하지만 view binding 방식을 사용하면 쉽게 전달이 가능함
class MyViewHolder(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)














