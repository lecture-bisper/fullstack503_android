package bitc.fullstack.app_20250311

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250311.databinding.ActivityMainBinding

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


    binding.btnLife.setOnClickListener {
      val intent = Intent(this, LifeActivity::class.java)
      startActivity(intent)
    }

    binding.btnToolbar.setOnClickListener {
      val intent = Intent(this, ToolbarActivity::class.java)
      startActivity(intent)
    }

    binding.btnFragment.setOnClickListener {
      val intent = Intent(this, FragmentActivity::class.java)
      startActivity(intent)
    }


  }
}











