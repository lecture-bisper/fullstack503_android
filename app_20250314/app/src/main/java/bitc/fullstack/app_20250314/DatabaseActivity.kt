package bitc.fullstack.app_20250314

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250314.databinding.ActivityDatabaseBinding
import bitc.fullstack.app_20250314.helper.DBHelper
import bitc.fullstack.app_20250314.helper.PhoneBookData

class DatabaseActivity : AppCompatActivity() {

  private val binding: ActivityDatabaseBinding by lazy {
    ActivityDatabaseBinding.inflate(layoutInflater)
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

//    SQLiteOpenHelper 클래스를 상속받은 DBHelper 클래스의 객체 생성
    val dbHelper = DBHelper(this)
//    writableDatabase : 읽기/쓰기 모드로 접속
//    readableDatabase : 읽기 전용 모드
    val database = dbHelper.writableDatabase

    binding.btnInsert.setOnClickListener {
      val name: String = binding.etName.text.toString()
      val phone: String = binding.etPhone.text.toString()
      val email: String = binding.etEmail.text.toString()
      val address: String = binding.etAddress.text.toString()

      val data = PhoneBookData(name = name, phone = phone, email = email, address = address)

      dbHelper.dbInsert(database, data)
      clearEditViewText()
    }

    binding.btnUpdate.setOnClickListener {
      val seq: Int = binding.etSeq.text.toString().toInt()
      val name: String = binding.etName.text.toString()
      val phone: String = binding.etPhone.text.toString()
      val email: String = binding.etEmail.text.toString()
      val address: String = binding.etAddress.text.toString()

      val data = PhoneBookData(seq = seq, name = name, phone = phone, email = email, address = address)

      dbHelper.dbUpdate(database, data)
      clearEditViewText()
    }

    binding.btnDelete.setOnClickListener {
      val seq: Int = binding.etSeq.text.toString().toInt()
      dbHelper.dbDelete(database, seq)
      clearEditViewText()
    }

    binding.btnSelectSeq.setOnClickListener {
      val seq: Int = binding.etSeq.text.toString().toInt()
      val result: String = dbHelper.dbSelectSeq(database, seq)
      binding.tvResult.setText(result)
      clearEditViewText()
    }

    binding.btnSelectName.setOnClickListener {
      val name: String = binding.etName.text.toString()
      val result: String = dbHelper.dbSelectName(database, name)
      binding.tvResult.setText(result)
      clearEditViewText()
    }

    binding.btnSelectAll.setOnClickListener {
      val result: String = dbHelper.dbSelectAll(database)
      binding.tvResult.setText(result)
      clearEditViewText()
    }

  }

  private fun clearEditViewText() {
    binding.etSeq.setText("")
    binding.etName.setText("")
    binding.etPhone.setText("")
    binding.etEmail.setText("")
    binding.etAddress.setText("")
  }
}









