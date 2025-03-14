package bitc.fullstack.app_20250313

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250313.databinding.ActivityPhotoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class PhotoActivity : AppCompatActivity() {

  private lateinit var binding: ActivityPhotoBinding
  private lateinit var filePath: String

  @SuppressLint("SimpleDateFormat")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityPhotoBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    사진첩 앱 사용 후 액티비티가 종료되면 실행됨
//    사진첩 앱의 사용 결과를 가져옴
    val requestGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
      try {
//        사진첩에서 가져오는 이미지의 크기를 설정
//        첫번째 매개변수로 파일 경로, X축, Y축 크기 설정
        val calRatio = calculateInSampleSize(
          it.data!!.data!!,
          resources.getDimensionPixelSize(R.dimen.imgSize),
          resources.getDimensionPixelSize(R.dimen.imgSize)
        )

//        BitmapFactory 를 사용하여 위에서 설정한 이미지 크기로 설정
        val option = BitmapFactory.Options()
        option.inSampleSize = calRatio

//        contentResolver 를 통해서 반환받은 이미지를 파싱
        var inputStream = contentResolver.openInputStream(it.data!!.data!!)
//        파싱된 이미지 데이터를 안드로이드 시스템에서 사용하는 이미지 형식으로 변환
        val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
//        파싱한 이미지 데이터를 변환했으므로 스트림 닫기
        inputStream!!.close()
        inputStream = null

//        xml 파일의 ImageView 태그에 가져온 이미지 데이터를 적용
        bitmap?.let {
          binding.ivUser.setImageBitmap(bitmap)
        } ?: let {
          Log.d("fullstack503", "이미지 없음")
        }
      }
      catch (e: Exception) {
        e.printStackTrace()
      }
    }


//    카메라 앱 사용 후 결과를 반환
    val requestCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//      카메라를 통해서 가져올 이미지의 크기를 설정
      val calRatio = calculateInSampleSize(
        Uri.fromFile(File(filePath)),
        resources.getDimensionPixelSize(R.dimen.imgSize),
        resources.getDimensionPixelSize(R.dimen.imgSize)
      )

//      BitmapFactory 에 위에서 설정한 이미지 크기를 설정
      val option = BitmapFactory.Options()
      option.inSampleSize = calRatio
//      카메라를 통해서 캡쳐된 이미지 파일을 가져와서 변수에 저장
      val bitmap = BitmapFactory.decodeFile(filePath, option)
//      xml 파일의 ImageView 태그에 적용
      bitmap?.let {
        binding.ivUser.setImageBitmap(bitmap)
      }
    }


//    사진첩 버튼 클릭 이벤트
    binding.btnGallery.setOnClickListener {
//      안드로이드의 사진첩 앱에 접근
//      ACTION_PICK 사용 시 사진첩 앱을 사용할 수 있음
      val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//      가져올 데이터를 이미지 타입으로 제한
      galleryIntent.type = "image/*"
//      인텐트 전달
      requestGalleryLauncher.launch(galleryIntent)
    }

    //    카메라 버튼 클릭 이벤트
    binding.btnCamera.setOnClickListener {
//      카메라로 캡쳐한 이미지의 파일명을 현재 시간을 기준으로 설정
      val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//      Environment : 시스템 정보를 가지고 있는 클래스, Environment.DIRECTORY_PICTURES 를 사용하여 사진이 저장된 폴더의 전체 경로를 받아옴
      val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//      지정한 위치에 File 클래스를 사용하여 파일 생성
      val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)

      filePath = file.absolutePath
//      안드로이드 시스테멩 저장할 파일 위치 설정
      val photoURI: Uri = FileProvider.getUriForFile(this, "bitc.fullstack.app_20250313.fileprovider", file)

//      카메라를 사용하기 위한 Intent 설정
//      MediaStore.ACTION_IMAGE_CAPTURE 는 카메라 호출
      val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//      안드로이드 시스템에 저장할 파일 위치 및 파일 이름을 putExtra() 사용하여 전달
      cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//      Intent 전달
      requestCameraLauncher.launch(cameraIntent)
    }
  }

//  각종 이벤트를 한번에 등록하기 위한 메소드
  private fun eventInit() {

  }

//  사진첩 및 카메라를 사용하여 가져온 이미지의 크기를 작게 설정하는 메소드
  private fun calculateInSampleSize(fileUri: Uri, reqWith: Int, reqHeight: Int): Int {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true

    try {
      var inputStream = contentResolver.openInputStream(fileUri)
      BitmapFactory.decodeStream(inputStream, null, options)
      inputStream!!.close()
      inputStream = null
    }
    catch (e: Exception) {
      e.printStackTrace()
    }

    val (height: Int, width: Int) = options.run {
      outHeight to outWidth
    }
    var inSampleSize = 1

    if (height > reqHeight || width > reqWith) {
      val halfHeight: Int = height / 2
      val halfWidth: Int = width / 2

      while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWith) {
        inSampleSize *= 2
      }
    }

    return inSampleSize
  }
}











