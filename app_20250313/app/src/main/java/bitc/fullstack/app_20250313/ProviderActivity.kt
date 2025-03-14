package bitc.fullstack.app_20250313

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250313.databinding.ActivityProviderBinding

class ProviderActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityProviderBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    val requestContactsLauncher = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()) {
      if (it.resultCode == RESULT_OK) {
//        Log.d("fullstack503", "${it.data?.data}")
        val cursor = contentResolver.query(
          it!!.data!!.data!!,
          arrayOf<String>(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null
        )

        Log.d("fullstack503", "cursor size ... ${cursor?.count}")
        if (cursor!!.moveToFirst()) {
          val name = cursor?.getString(0)
          val phone = cursor?.getString(1)
          Log.d("fullstack503", "name : $name, number : $phone")
          Toast.makeText(this, "name : $name, number : $phone", Toast.LENGTH_SHORT).show()
        }
      }
    }

//    전화앱 사용 버튼
    binding.btnPhone.setOnClickListener {
//      전화앱 사용 시 옵션으로 ACTION_DIAL, ACTION_CALL 이 있음
//      ACTION_DIAL : 기본 전화앱을 열고 사용자가 전화 버튼을 누를 수 있도록 함 (사용 권장)
//      ACTION_CALL : 기본 전화앱을 열고 자동으로 통화를 진행, 추가 권한이 필요함
      val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"))
      startActivity(intent)
    }

//    지도 앱 사용 버튼
    binding.btnMap.setOnClickListener {
      val uri = Uri.parse("geo:35.158793,129.160252")
      val intent = Intent(Intent.ACTION_VIEW, uri)
      intent.setPackage("com.google.android.apps.maps")
      startActivity(intent)
    }

//    주소록 가져오기 버튼
    binding.btnPhoneBook.setOnClickListener {
      val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
      requestContactsLauncher.launch(intent)
    }

//    갤러리 및 카메라로 이미지 변경하기
    binding.btnPhoto.setOnClickListener {
      val intent = Intent(this, PhotoActivity::class.java)
      startActivity(intent)
    }
  }
}









