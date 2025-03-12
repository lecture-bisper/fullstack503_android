package bitc.fullstack.app_20250311

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250311.databinding.ActivityLife2Binding

class Life2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityLife2Binding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("fullstack503", "*** Life2Activity - onCreate() 호출 ***")

  }

  override fun onStart() {
    super.onStart()

    Log.d("fullstack503", "*** Life2Activity - onStart() 호출 ***")
  }

  override fun onResume() {
    super.onResume()

    Log.d("fullstack503", "*** Life2Activity - onResume() 호출 ***")
  }

  override fun onPause() {
    super.onPause()

    Log.d("fullstack503", "*** Life2Activity - onPause() 호출 ***")
  }

  override fun onStop() {
    super.onStop()

    Log.d("fullstack503", "*** Life2Activity - onStop() 호출 ***")
  }

  override fun onRestart() {
    super.onRestart()

    Log.d("fullstack503", "*** Life2Activity - onRestart() 호출 ***")
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d("fullstack503", "*** Life2Activity - onDestroy() 호출 ***")
  }
}