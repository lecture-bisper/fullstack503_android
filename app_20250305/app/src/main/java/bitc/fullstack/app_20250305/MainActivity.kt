package bitc.fullstack.app_20250305

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250305.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private var flag: Boolean = false

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

    binding.btn01.setOnClickListener {

      if (flag) {
        flag = false
        binding.ivIcon.visibility = View.INVISIBLE
      }
      else {
        flag = true
        binding.ivIcon.visibility = View.VISIBLE
      }
//      binding.btn01.visibility = View.INVISIBLE
//      binding.ivIcon.visibility = View.VISIBLE
    }

    binding.ivIcon.setOnClickListener {
      binding.btn01.visibility = View.VISIBLE
      binding.ivIcon.visibility = View.INVISIBLE
    }
  }
}