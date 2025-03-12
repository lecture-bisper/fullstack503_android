package bitc.fullstack.app_20250311

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250311.databinding.ActivityLifeBinding

class LifeActivity : AppCompatActivity() {

  private var saveData = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityLifeBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }


    Log.d("fullstack503", "### LifeActivity - onCreate() 호출 ###")

    binding.btnOpenLife2.setOnClickListener {
      val intent = Intent(this, Life2Activity::class.java)
      startActivity(intent)
    }
  }

  override fun onStart() {
    super.onStart()

    Log.d("fullstack503", "### LifeActivity - onStart() 호출 ###")
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)

    Log.d("fullstack503", "### LifeActivity - onRestoreInstanceState() 호출 ###")

    saveData = savedInstanceState.getInt("saveData", 0)
    Log.d("fullstack503", "### onRestoreInstanceState 로 복구한 데이터 : $saveData ###")
  }

  override fun onResume() {
    super.onResume()

    Log.d("fullstack503", "### LifeActivity - onResume() 호출 ###")
    Log.d("fullstack503", "### onResume 에서 현재 saveData : $saveData ###")
  }

  override fun onPause() {
    super.onPause()

    Log.d("fullstack503", "### LifeActivity - onPause() 호출 ###")
  }

  override fun onStop() {
    super.onStop()

    Log.d("fullstack503", "### LifeActivity - onStop() 호출 ###")
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)

    Log.d("fullstack503", "### LifeActivity - onSaveInstanceState() 호출 ###")

    saveData += 1
    outState.putInt("saveData", saveData)
    Log.d("fullstack503", "### LonSaveInstanceState로 저장한 데이터 : $saveData ###")
  }

  override fun onRestart() {
    super.onRestart()

    Log.d("fullstack503", "### LifeActivity - onRestart() 호출 ###")
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d("fullstack503", "### LifeActivity - onDestroy() 호출 ###")
  }

}









