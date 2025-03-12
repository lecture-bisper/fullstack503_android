package bitc.fullstack.app_20250312

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250312.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.btnRecyclerView.setOnClickListener {
      val intent = Intent(this, RecyclerViewActivity::class.java)
      startActivity(intent)
    }

    binding.btnVeiwPager.setOnClickListener {
      val intent = Intent(this, ViewPagerActivity::class.java)
      startActivity(intent)
    }

    binding.btnDrawer.setOnClickListener {
      val intent = Intent(this, DrawerActivity::class.java)
      startActivity(intent)
    }

    binding.btnReceiver.setOnClickListener {
      val intent = Intent(this, ReceiverActivity::class.java)
      startActivity(intent)
    }
  }
}

//  문제 1) 리사이클러뷰를 사용하여 카톡 채팅 리스트 화면을 작성하세요.









