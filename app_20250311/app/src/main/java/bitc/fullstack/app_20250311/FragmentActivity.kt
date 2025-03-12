package bitc.fullstack.app_20250311

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import bitc.fullstack.app_20250311.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityFragmentBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    프래그먼트를 제어하는 프래그먼트 매니저 가져오기
    val fragmentManager: FragmentManager = supportFragmentManager
//    SQL의 트랜젝션과 비슷한 기능을 하는 FragmentTransaction 객체를 FragmentManager를 사용하여 생성
//    commit() 를 호출하면 지정된 프래그먼트 를 화면에 출력함 (현재는 빈 화면)
    var transaction: FragmentTransaction = fragmentManager.beginTransaction()

//    사용자가 만들어 놓은 프래그먼트를 사용하여 객체 생성
    val fragment1 = Fragment1()
    val fragment2 = Fragment2()

//    add() : FragmentTransaction 에 프래그먼트를 추가
//    replace() : 이미 등록된 프래그먼트를 지정한 프래그먼트로 교체
//    remove() : 이미 등록된 프래그먼트를 제거
    transaction.add(R.id.layout_fragment_base, fragment1)
//    commit() : FragmentTransaction에 등록된 프래그먼트를 화면에 출력
    transaction.commit()

    binding.btnFrag1.setOnClickListener {
//      프래그먼트 변경 시 트랜젝션을 새로 지정
      transaction = fragmentManager.beginTransaction()
//      등록된 프래그먼트를 지정한 위치에 지정한 프래그먼트로 교체
      transaction.replace(R.id.layout_fragment_base, fragment1)
//      트랜젝션과 관련된 화면 전환 및 상태 변경을 최적화
      transaction.setReorderingAllowed(true)
//      프래그먼트를 사용하여 여러번 화면을 변경했을 경우 변경 내역을 저장하고 있다가 뒤로 가기 버튼을 클릭 시 저장된 프래그먼트로 이동
//      addToBackStack() 를 사용하지 않으면 저장되는 내용이 없기 때문에 프래그먼트를 변경하여 화면을 전환 시 기존의 프래그먼트는 삭제 됨
      transaction.addToBackStack(null)
//      지정된 프래그먼트로 화면 전환
      transaction.commit()
    }

    binding.btnFrag2.setOnClickListener {
      transaction = fragmentManager.beginTransaction()
      transaction.replace(R.id.layout_fragment_base, fragment2)
      transaction.setReorderingAllowed(true)
      transaction.addToBackStack("")
      transaction.commit()
    }
  }
}










