package bitc.fullstack.app_20250312

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import bitc.fullstack.app_20250312.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityViewPagerBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    ViewPager2 에서 사용할 프래그먼트 리스트를 생성
    val fragmentList = listOf<Fragment>(OneFragment(), TwoFragment(), ThreeFragment())
//    ViewPager2 의 어뎁터 객체를 생성
    val viewPager2Adapter = MyViewPagerAdapter(this)

//    생성된 어뎁터에 프래그먼트 리스트를 적용
    viewPager2Adapter.fragmentList = fragmentList
//    xml UI 파일에 있는 ViewPager2 태그에 생성한 어뎁터를 적용
    binding.viewPagerFragment.adapter = viewPager2Adapter

  }
}







