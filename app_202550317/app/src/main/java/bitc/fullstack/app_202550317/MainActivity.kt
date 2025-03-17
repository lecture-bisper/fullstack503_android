package bitc.fullstack.app_202550317

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_202550317.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    initEventListener()
  }

  private fun initEventListener() {

    binding.btnCallState.setOnClickListener {
      val intent = Intent(this, CallStateActivity::class.java)
      startActivity(intent)
    }

    binding.btnHttpConnection.setOnClickListener {
      val intent = Intent(this, HttpConnectionActivity::class.java)
      startActivity(intent)
    }

    binding.btnGlide.setOnClickListener {
      val intent  = Intent(this, GlideActivity::class.java)
      startActivity(intent)
    }
  }
}








