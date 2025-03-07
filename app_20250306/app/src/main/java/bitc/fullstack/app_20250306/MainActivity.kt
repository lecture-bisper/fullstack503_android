package bitc.fullstack.app_20250306

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250306.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private var saveNum1: Int = 0
  private var saveNum2: Int = 0
  private var op: String = "+"

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

    binding.btn0.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "0"
      }
      else {
        num += "0"
      }
      binding.tvResult.text = num
    }

    binding.btn1.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "1"
      }
      else {
        num += "1"
      }
      binding.tvResult.text = num
    }

    binding.btn2.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "2"
      }
      else {
        num += "2"
      }
      binding.tvResult.text = num
    }

    binding.btn3.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "3"
      }
      else {
        num += "3"
      }
      binding.tvResult.text = num
    }

    binding.btn4.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "4"
      }
      else {
        num += "4"
      }
      binding.tvResult.text = num
    }

    binding.btn5.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "5"
      }
      else {
        num += "5"
      }
      binding.tvResult.text = num
    }

    binding.btn6.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "6"
      }
      else {
        num += "6"
      }
      binding.tvResult.text = num
    }

    binding.btn7.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "7"
      }
      else {
        num += "7"
      }
      binding.tvResult.text = num
    }

    binding.btn8.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "8"
      }
      else {
        num += "8"
      }
      binding.tvResult.text = num
    }

    binding.btn9.setOnClickListener {
      var num = binding.tvResult.text.toString()
      if (num == "0") {
        num = "9"
      }
      else {
        num += "9"
      }
      binding.tvResult.text = num
    }

    binding.btnSum.setOnClickListener {
      saveNum1 = binding.tvResult.text.toString().toInt()
      op = "+"
      binding.tvResult.text = "0"
    }

    binding.btnSub.setOnClickListener {
      saveNum1 = binding.tvResult.text.toString().toInt()
      op = "-"
      binding.tvResult.text = "0"
    }

    binding.btnMulti.setOnClickListener {
      saveNum1 = binding.tvResult.text.toString().toInt()
      op = "*"
      binding.tvResult.text = "0"
    }

    binding.btnDiv.setOnClickListener {
      saveNum1 = binding.tvResult.text.toString().toInt()
      op = "/"
      binding.tvResult.text = "0"
    }

    binding.btnResult.setOnClickListener {
      saveNum2 = binding.tvResult.text.toString().toInt()
      var result = 0

      when (op) {
        "+" -> result = saveNum1 + saveNum2
        "-" -> result = saveNum1 - saveNum2
        "*" -> result = saveNum1 * saveNum2
        "/" -> result = saveNum1 / saveNum2
      }

      binding.tvResult.text = result.toString()
    }

    binding.btnClear.setOnClickListener {
      saveNum1 = 0
      saveNum2 = 0
      op = "+"
      binding.tvResult.text = "0"
    }
  }
}













