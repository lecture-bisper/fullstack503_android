package bitc.fullstack.app_20250304

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val name = TextView(this).apply {
      typeface = Typeface.DEFAULT_BOLD
      text = "Lake Louise"
    }

    val image = ImageView(this).also {
      it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dog01))
    }

    val address = TextView(this).apply {
      typeface = Typeface.DEFAULT_BOLD
      text = "Lake Louise, AB, 캐나다"
    }

    val layout = LinearLayout(this).apply {
      orientation = LinearLayout.VERTICAL
      gravity = Gravity.CENTER

      addView(name, WRAP_CONTENT, WRAP_CONTENT)
      addView(image, WRAP_CONTENT, WRAP_CONTENT)
      addView(address, WRAP_CONTENT, WRAP_CONTENT)
    }

    setContentView(layout)
  }
}











