package bitc.fullstack.app_20250314

import android.content.ContentUris
import android.content.ContentValues
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250314.databinding.ActivityFileBinding
import java.io.File

class FileActivity : AppCompatActivity() {

  private val binding: ActivityFileBinding by lazy {
    ActivityFileBinding.inflate(layoutInflater)
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
//    내부 저장소에 저장하는 파일은 특별한 권한없이 바로 접근이 가능함
//    외부에서 특정 앱의 내부 저장소에 접근하는 것은 안됨
//    내부 저장소는 용량이 작음
    
//    내부 저장소에 파일 쓰기
    binding.btnSystemInFileWrite.setOnClickListener {
//      filesDir : 현재 앱의 내부 저장소 경로
//      자바의 File 클래스를 사용하여 내부저장소 경로에 지정한 파일명으로 객체 생성
      val file = File(filesDir, "system_in_test.txt")
//      파일을 쓰기 위한 스트림을 생성
      val writeStream = file.writer()
//      write() 를 사용하여 실제로 파일 쓰기
      writeStream.write("hello world!!\n")
      writeStream.write("안녕하세요..\n")
      writeStream.write("내부 저장소에 파일 저장!!")
//      파일에 내용 쓰기 적용 및 메모리 버퍼를 비운 후 파일 닫기
      writeStream.flush()
      writeStream.close()
      Toast.makeText(this, "내부 저장소에 파일 저장 완료!!", Toast.LENGTH_SHORT).show()
    }

//    내부 저장소에서 파일 읽기
    binding.btnSystemInFileRead.setOnClickListener {

//      자바의 File 클래스를 사용하여 내부저장소 경로에 있는 지정한 파일명으로 객체 생성
      val file = File(filesDir, "system_in_test.txt")
//      BufferedReader 스트림을 생성
      val readStream = file.reader().buffered()
//      파일 안의 내용을 한 줄씩 읽어옴
      readStream.forEachLine {
        Log.d("fullstack503", "파일 내용 : $it")
      }
      readStream.close()
      Toast.makeText(this, "내부 저장소에서 파일 읽기 완료!!", Toast.LENGTH_SHORT).show()
    }

//    외부 저장소의 앱 전용 저장소는 권한이 필요 없도록 변경됨
//    매니페스트 파일에 외부 저장소 사용 권한을 등록
    
//    외부 저장소에 파일 쓰기 버튼 이벤트
    binding.btnSystemOutFileWrite.setOnClickListener {
//      getExternalFilesDir : 현재 앱의 외부 저장소 경로
      val file = File(getExternalFilesDir(null), "system_out_test.txt")
      val writeStream = file.writer()
      writeStream.write("외부 저장소에 파일 쓰기!!\n")
      writeStream.write("두번째 줄 쓰기")
      writeStream.flush()
      writeStream.close()
      Toast.makeText(this, "외부 저장소에 파일 쓰기 완료!!", Toast.LENGTH_SHORT).show()
    }

//    외부 저장소에서 파일 읽기 버튼 이벤트
    binding.btnSystemOutFileRead.setOnClickListener {
      val file = File(getExternalFilesDir(null), "system_out_test.txt")
      val readStream = file.reader().buffered()
      readStream.forEachLine {
        Log.d("fullstack503", "파일 내용 : $it")
      }
      readStream.close()
      Toast.makeText(this, "외부 저장소에서 파일 읽기 완료!!", Toast.LENGTH_SHORT).show()
    }

//    공용 Download 폴더에 파일 쓰기 이벤트
    binding.btnDownloadFileWrite.setOnClickListener {
//      파일 쓰기 위한 메타 데이터 생성
//      공용 폴더에 접근 시 MediaStore 클래스를 반드시 사용해야 함
      val contentValue = ContentValues().apply {
//        DISPLAY_NAME : 파일 이름
//        MIME_TYPE : 파일 타입
//        RELATIVE_PATH : 공용 폴더 경로
        put(MediaStore.MediaColumns.DISPLAY_NAME, "download_test.txt") // 파일 이름 설정
        put(MediaStore.MediaColumns.MIME_TYPE, "text/plain") // 파일 타입 설정
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS) // 공용 폴더 위치 설정, 다운로드폴더
      }

//      공용 폴더에 데이터를 읽기/쓰기 시 contentResolver 를 사용해야 함
//      위에서 ContentValues() 를 사용하여 생성한 메타 데이터로 파일을 쓰기 위한 정보를 설정
      val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValue)

//      uri 정보가 있는지 확인 후 있을 경우에만 실행
      uri?.let { uri ->
//        contentResolver 를 통해서 파일을 쓰기 위한 OutputStream 을 생성
        contentResolver.openOutputStream(uri)?.use { outputStream ->
//          contentResolver 를 통해서 받아온 OutputStream 을 통해서 파일에 내용을 씀
          outputStream.write("공용 저장소 Download 폴더에 데이터 저장".toByteArray())
          outputStream.write("공용 저장소 Download 폴더에 두번째 줄 저장".toByteArray())
        }
      }
    }

//    공용 Download 폴더에서 파일 읽기 이벤트
    binding.btnDownloadFileRead.setOnClickListener {
//      읽을 파일의 저장소 위치 설정
      val collection = MediaStore.Files.getContentUri("external")
      val projection = arrayOf(MediaStore.Files.FileColumns._ID)
//      읽어올 파일명 설정
      val selection = "${MediaStore.Files.FileColumns.DISPLAY_NAME} = ?"
      val selectionArgs = arrayOf("download_test.txt")

//      위에서 설정한 메타 데이터를 기반으로 contentResolver 를 사용하여 파일 정보를 가져옴
      contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
//        가져온 정보를 하나씩 출력
        if (cursor.moveToNext()) {
          val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
//          가져올 파일 uri 를 설정
          val uri = ContentUris.withAppendedId(collection, id)

//          contentResolver 를 사용하여 InputStream 을 받아옴
          contentResolver.openInputStream(uri)?.use { inputStream ->
//            가져온 InputStream 으로 파일의 내용을 읽어옴
            val fileContent = inputStream.bufferedReader().use { it.readText() }
            Log.d("fullstack503", "파일의 내용 : $fileContent")
          }
        }
      }
    }
  }
}










