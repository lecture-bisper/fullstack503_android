package bitc.fullstack.app_20250313

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250313.databinding.ActivityMainBinding

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

    binding.btnService.setOnClickListener {
      val intent = Intent(this, ServiceActivity::class.java)
      startActivity(intent)
    }

    binding.btnMp3Player.setOnClickListener {
      val intent = Intent(this, MP3Activity::class.java)
      startActivity(intent)
    }

    binding.btnContentProvider.setOnClickListener {
      val intent = Intent(this, ProviderActivity::class.java)
      startActivity(intent)
    }


  }
}









