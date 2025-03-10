package bitc.fullstack.app_20250307

import android.app.DatePickerDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_20250307.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

  private lateinit var notification: Uri
  private lateinit var ringtoneManager: Ringtone
  private lateinit var mediaPlayer: MediaPlayer

  @RequiresApi(Build.VERSION_CODES.R)
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

//    토스트 메시지 출력 버튼
    binding.btnToast.setOnClickListener {
//      토스트 메시지 기본 사용 방법
      Toast.makeText(this, "토스트 메시지 사용하기", Toast.LENGTH_SHORT).show()
    }

//    토스트 메시지 콜백 기능 사용 버튼
    binding.btnToastCallback.setOnClickListener {
//      미리 생성해 놓은 메소드를 해당 버튼 이벤트 시 호출
      toastCallback()
    }

//    데이트 픽커 사용 버튼
    binding.btnDatePicker.setOnClickListener {
//      데이트 픽커 다이얼로그 함수 호출
//      첫번째 매개변수 : 출력할 화면 선택 (현재 엑티비티를 선택, this)
//      두번째 매개변수 : 이벤트 리스너와 구현된 이벤트 핸들러
//      세번째, 네번째, 다섯번째 매개변수 : 각각 년도, 월, 일 을 입력
//        월은 0 ~ 11 까지로 표현됨 (0 = 1월, 1 = 2월, 11 = 12월)
      DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
//        구현된 이벤트 핸들러, onDateSet() 를 오버라이딩하여 사용
//        첫번째 매개변수 : 데이터 픽커 클래스, 일종의 디자인이라고 보면 됨
//        두번째, 세번째, 네번째 매개변수 : 각각 년도, 월, 일
//          월은 0 ~ 11 까지로 표현됨 (0 = 1월, 1 = 2월, 11 = 12월)
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
          Log.d("fullstack503", "year : $year, month : ${month + 1}, day : $dayOfMonth")
          Toast.makeText(this@MainActivity, "선택한 날짜 : $year-${month + 1}-$dayOfMonth", Toast.LENGTH_SHORT).show()
        }
      }, 2025, 3 - 1, 7).show()
    }

//    타임 픽커 사용 버튼
    binding.btnTimePicker.setOnClickListener {
//      타임 픽커 다이얼로그 함수 호출
//      첫번째 매개변수 : 출력할 화면 선택 (현재 엑티비티를 선택, this)
//      두번째 매개변수 : 이벤트 리스너와 구현된 이벤트 핸들러
//      세번째, 네번째, 다섯번째 매개변수 : 각각 시간, 분, 24시간 타입 표기 여부
      TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener {
//        구현된 이벤트 핸들러, onTimeSet() 를 오버라이딩하여 사용
//        첫번째 매개변수 : 타임 픽커 클래스, 일종의 디자인이라고 보면 됨
//        두번째, 세번째 매개변수 : 각각 시간, 분
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
          Log.d("fullstack503", "time : $hourOfDay, minute : $minute")
          Toast.makeText(this@MainActivity, "선택한 시간 : $hourOfDay : $minute", Toast.LENGTH_SHORT).show()
        }
      }, 11, 4, true).show()
    }

//    알림 다이얼로그 사용하기
    binding.btnAlertDialog.setOnClickListener {
//      알림 다이얼로그 사용 시 Builder()를 사용하여 다이얼로그 화면을 설정
      AlertDialog.Builder(this).run {

//        알림 다이얼로그의 버튼 클릭 시 동작할 내용을 설정
        val eventHandler = object: DialogInterface.OnClickListener {
//          실제 버튼 클릭 시 동작할 메소드를 오버라이딩
//          두번째 매개변수로 사용자가 클릭한 버튼의 정보
          override fun onClick(dialog: DialogInterface?, which: Int) {
//            확인 버튼 클릭 시
            if (which == DialogInterface.BUTTON_POSITIVE) {
//              this@엑티비티명 : 원래는 this 를 사용 시 현재 엑티비티의 객체를 의미함
//              this@MainAcitivy 는 현재 this 가 가르키는 객체가 변경되었기 때문
//              현재 익명객체 안에서 this를 사용했기 때문에 this는 익명객체를 의미함
//              지정한 토스트 메시지를 출력할 위치는 MainActivity 이므로 this@MainActivity 로 표현함
              Toast.makeText(this@MainActivity, "확인 버튼 클릭!!", Toast.LENGTH_SHORT).show()
            }
//            취소 버튼 클릭 시
            else if (which == DialogInterface.BUTTON_NEGATIVE) {
              Toast.makeText(this@MainActivity, "취소 버튼 클릭!!", Toast.LENGTH_SHORT).show()
            }
//            추가 버튼 클릭 시
            else {
              Toast.makeText(this@MainActivity, "More 버튼 클릭!!", Toast.LENGTH_SHORT).show()
            }
          }
        }

//        제목 설정
        setTitle("알림 다이얼로그 사용하기")
//        제목 앞의 아이콘 설정
        setIcon(android.R.drawable.ic_dialog_info)
//        알림 다이얼로그 body 부분의 메시지 설정
        setMessage("알림창을 닫으시겠습니까?")
//        setPositiveButton(), setNegativeButton(), setNeutralButton() 를 설정해야 다이얼로그 화면에 버튼이 출력됨
//        확인 버튼 등록, 첫번째 변수로 버튼의 텍스트 설정, 두번째 매개변수로 실제 동작할 이벤트 핸들러 설정
        setPositiveButton("OK", eventHandler)
//        취소 버튼 등록
        setNegativeButton("Cancel", eventHandler)
//        추가 버튼 등록
//        setNeutralButton("More", eventHandler)
        show()
      }
    }

//    알림 다이얼로그 목록 사용하기
    binding.btnAlertDialogList.setOnClickListener {
//      알림 다이얼로그 화면에 출력할 데이터 배열 생성
      val items = arrayOf<String>("사과", "배", "수박", "포도", "복숭아")

      AlertDialog.Builder(this).run {
        setIcon(android.R.drawable.ic_dialog_info)
        setTitle("알림 다이얼로그에 목록 사용")
//        알림 다이얼로그에 출력할 목록을 설정
//        해당 목록의 아이템을 선택 시 동작할 이벤트 리스너 및 이벤트 핸들러를 구현
//        setItems() : 알림 다이얼로그의 body 부분에 일반 텍스트가 아닌 목록을 출력하는 메소드
        setItems(items, object : DialogInterface.OnClickListener {
//          두번째 매개변수로 사용자가 클릭한 목록의 index 번호를 전달받음
          override fun onClick(dialog: DialogInterface?, which: Int) {
            Log.d("fullstack503", "선택한 과일 : ${items[which]}")
            Toast.makeText(this@MainActivity, "선택한 과일 : ${items[which]}", Toast.LENGTH_SHORT).show()
          }
        })
//        이벤트 핸들러가 필요 없을 경우 두번째 매개변수를 null로 처리
        setPositiveButton("닫기", null)
        show()
      }
    }

//    알림 다이얼로그 체크박스 사용하기
    binding.btnAlertDialogCheckbox.setOnClickListener {
//      기존 알림 다이얼로그 목록 사용하기와 기본적으로 같음
//      화면에 출력할 체크박스의 목록 생성하기
      val items = arrayOf("사과", "배", "복숭아", "수박", "참외", "포도")

//      알림 다이얼로그 설정
      AlertDialog.Builder(this).run { 
        setIcon(android.R.drawable.ic_dialog_info)
        setTitle(resources.getString(R.string.alert_dialog_Checkbox))
//        알림 다이얼로그에 체크박스 설정
//        setMultiChoiceItems() : 알림 다이얼로그에 체크박스를 설정하는 메소드
//        첫번째 매개변수 : 체크박스 목록 데이터
//        두번째 매개변수 : 지정한 체크박스 목록의 기본 체크 여부 설정
//        세번째 매개변수 : 이벤트 리스너와 이벤트 핸들러 설정 및 구현
        setMultiChoiceItems(items, booleanArrayOf(false, true, true, true, false, false), object : DialogInterface.OnMultiChoiceClickListener {
//          onClick() : 두번째 매개변수는 사용자가 클릭한 index 번호, 세번째 매개변수는 해당 index 의 체크박스의 체크 여부
          override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
            Log.d("fullstack503", "${items[which]} 이 ${if(isChecked) "선택되었습니다." else "선택 해제 되었습니다."}")
            Toast.makeText(this@MainActivity, "${items[which]} 이 ${if(isChecked) "선택되었습니다." else "선택 해제 되었습니다."}", Toast.LENGTH_SHORT).show()
          }
        })
        setPositiveButton("닫기", null)
        show()
      }
    }

//    알림 다이얼로그 라디오버튼 사용하기
    binding.btnAlertDialogRadio.setOnClickListener {
//      기존 알림 다이얼로그 목록 사용하기와 기본적으로 같음
//      화면에 출력할 라디오버튼의 목록 생성하기
      val items = arrayOf("사과", "배", "복숭아", "수박", "참외", "포도")

//      알림 다이얼로그 설정
      AlertDialog.Builder(this).run {
        setIcon(android.R.drawable.ic_dialog_info)
        setTitle("알림 다이얼로그에 라디오버튼 사용")
//        setSingleChoiceItems() : 알림 다이얼로그에서 라디오버튼을 사용하기 위함 메소드
//        첫번째 매개변수 : 알림 다이얼로그에 출력할 라디오 버튼 목록 데이터
//        두번째 매개변수 : 알림 다이얼로그 출력 시 기본값으로 선택할 index 번호
//        세번째 매개변수 : 이벤트 리스너와 이벤트 핸들러 설정 및 구현
        setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
//          두번째 매개변수로 사용자가 클릭한 목록의 index 번호를 전달받음
          override fun onClick(dialog: DialogInterface?, which: Int) {
            Log.d("fullstack503", "${items[which]} 이 선택되었습니다.")
            Toast.makeText(this@MainActivity, "${items[which]} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
          }
        })
        setPositiveButton("닫기", null)
//        setCancelable() : 알림 다이얼로그의 확인/취소 버튼을 눌러야지만 알림 다이얼로그를 닫게하는 설정, 기본값 true
        setCancelable(false)
        show()
//        setCanceledOnTouchOutside() : 알림 다이얼로그의 버튼을 제외한 다른 화면을 클릭 시 자동 취소를 하지않기 위한 설정, 기본값 true
      }.setCanceledOnTouchOutside(false)
    }

//    일반 변수로 선언하여 출력 중인 사운드 객체를 1개로 제한
//      안드로이드 시스템의 RingtoneManager를 사용하여 안드로이드 기본 설정 사운드를 출력하도록 설정
//    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
////      지정한 사운드를 출력할 안드로이드 시스템에 전달, 현재 실행하고 있는 앱을 의미함
//    val ringtoneManager = RingtoneManager.getRingtone(applicationContext, notification)

    binding.btnAlertSound.setOnClickListener {
      Toast.makeText(this, "링톤 매니저를 이용하여 시스템 사운드 출력", Toast.LENGTH_LONG).show()

      if (ringtoneManager.isPlaying) {
        ringtoneManager.stop()
      }
      else {
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtoneManager = RingtoneManager.getRingtone(applicationContext, notification)
        ringtoneManager.play()
      }
    }

//    MediaPlayer 을 사용하여 알림 사운드 출력하기
    binding.btnAlertMediaPlayer.setOnClickListener {
      Toast.makeText(this, "Media Player를 이용하여 시스템 사운드 출력", Toast.LENGTH_SHORT).show()

      mediaPlayer = MediaPlayer.create(this, R.raw.tracehone)
      mediaPlayer.start()
    }

//    MediaPlayer 을 사용하여 출력한 알림 사운드 정지
    binding.btnAlertMediaPlayerStop.setOnClickListener {
      Toast.makeText(this, "Media Player를 이용한 시스템 사운드 출력 정지", Toast.LENGTH_SHORT).show()

      mediaPlayer.stop()
    }

//    진동 알림 사용하기
    binding.btnAlertVibrator.setOnClickListener {
//      현재 스마트폰의 버전이 지정한 API 버전과 같거나 높은지 확인, VibratorManager 는 API 31 버전부터 사용 가능
      val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//        getSystemService() : 안드로이드 시스템의 서비스를 가져오는 메소드, VIBRATOR_MANAGER_SERVICE 를 가져옴

        val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
      }
      else {
//        VIBRATOR_SERVICE : API 31 이전 버전 사용 시 
        getSystemService(VIBRATOR_SERVICE) as Vibrator
      }

//      현재 사용하는 안드로이드 API 버전이 API 26 와 같거나 높은지 확인
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        vibrate() : API 26 버전 이상 사용 시 사용하는 함수
//        VibrationEffect : 진동 세기를 지정할 수 있음
//        createOneShot() : 동일한 형태의 진동 효과를 사용
//        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))

//        createWaveform() : 지정한 배열에 따라 진동 패턴 및 진동의 세기를 조절하여 진동 알림을 사용할 수 있음
        vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000), intArrayOf(0, 50, 0, 200), -1))
      }
      else {
//        vibrate() : 이전 버전 사용 시 사용하는 함수
//        vibrator.vibrate(1000)

//        vibrate() 에 long 타입 배열 사용 시 진동 패턴을 사용하는 진동 알림을 사용할 수 있음
        vibrator.vibrate(longArrayOf(500, 1000, 500, 2000), -1)
      }
    }


//    안드로이드 상단 알림창 사용하기
    binding.btnNotification.setOnClickListener {
//      NotificationManager 타입의 객체를 안드로이드 시스템에서 NOTIFICATION_SERVICE 가져와서 생성함
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//      NotificationCompat 타입 변수 생성
      val builder: NotificationCompat.Builder

//      현재 안드로이드 API 버전이 API 33 와 같거나 높은지 확인
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        API 33 이상부터 채널 정보가 반드시 필요함
        val channelId = "one-channel" // 채널 아이디 생성, 문자열로 원하는대로 입력 (중복만 안되도록 생성)
        val channelName = "My One Channel" // 채널 이름 생성, 문자열로 원하는대로 입력
//        NotificationChannel 생성
//        IMPORTANCE_HIGH : 긴급 알림, 알림음을 출력, 화면 상단에 표시
//        IMPORTANCE_DEFAULT : 높은 중요도, 알림음 출력
//        IMPORTANCE_LOW : 중간 중요도, 알림음 없음
//        IMPORTANCE_MIN : 낮은 중요도, 알림음 없음, 상태바에 표시 안됨
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "알림 채널 설명"
//        앱 아이콘에 알림 숫자 설정
        channel.setShowBadge(true)

//        알림음 출력 설정
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
//          setContentType() : 알림, 벨소리, 시스템 소리등을 위한 오디오 유형을 설정
//          CONTENT_TYPE_SONIFICATION : 알림 및 시스템 소리
//          CONTENT_TYPE_MUSIC : 음악 앱 및 스트리밍 서비스
//          CONTENT_TYPE_SPEECH : TTS 및 음성 메시지
//          CONTENT_TYPE_MOVIE : 동영상 플레이어
//          CONTENT_TYPE_UNKNOWN : 특정 카테고리에 없는 오디오
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()

//        알림음 사용 설정
        channel.setSound(uri, audioAttributes)
//        알림 시 플래시
        channel.enableLights(true)
        channel.lightColor = Color.RED
//        알림 시 진동
        channel.enableVibration(true)
//        알림 진동 패턴 등록
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

//        시스템 서비스를 사용하여 NotificationChannel 을 등록
        manager.createNotificationChannel(channel)

//        NotificationCompat 객체를 Builder()로 생성
        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
//        옛날 버전의 NotificationCompat 객체 생성
        builder = NotificationCompat.Builder(this)
      }

//      상단 알림창의 아이콘 설정
      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
//      상단 알림창의 알림 발생 시간
      builder.setWhen(System.currentTimeMillis())
//      상단 알림창 제목
      builder.setContentTitle("상단 알림창 제목")
//      상단 알림창 내용
      builder.setContentText("상단 알림창 내용")
//      상단 알림창 클릭 시 아이콘에 존재하는 알림 숫자를 제거
      builder.setAutoCancel(true)

//      상단 알림의 터치 이벤트 : 상단 알림 자체가 현재 앱에서 동작하는 것이 아니라 안드로이드 시스템에 실행해 달라고 요청하는 것이기 때문에 상단 알림의 터치 이벤트도 직접 실행할 수 없음
//        각종 이벤트를 모두 안드로이드 시스템에 동작 시켜 달라고 요청해야 함
//        이러한 요청을 Intent 라는 객체가 안드로이드 시스템에 대신 요청해 줌

//      화면 전환을 위한 컴포넌트
//      첫번째 매개변수를 사용하여 전환할 앱을 선택 (현재 앱을 의미)
//      두번째 매개변수를 사용하여 원하는 엑티비티로 이동
      val intent = Intent(this, NotificationActivity::class.java)
//      안드로이드 시스템에 요청하여 지정한 엑티비티로 이동
      val pendingIntent = PendingIntent.getActivity(this, 12, intent, PendingIntent.FLAG_IMMUTABLE)
//      상단 알림창에 intent 정보 등록
      builder.setContentIntent(pendingIntent)


//      안드로이드 시스템 서비스에 상단 알림창 출력 요청
//      첫번째 매개변수는 안드로이드 시스템 서비스에 요청 시 사용하는 ID
      manager.notify(11, builder.build())
    }

//    상단 알림창의 액션버튼 사용하기
    binding.btnNotificationAction.setOnClickListener{
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val builder: NotificationCompat.Builder

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "액션 버튼 ID"
        val channelName = "알림 액션 버튼"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "알림 채널 액션 설명 부분"
        channel.setShowBadge(true)

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()

        channel.setSound(uri, audioAttributes)
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

        manager.createNotificationChannel(channel)

        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
      builder.setWhen(System.currentTimeMillis())
      builder.setContentTitle("상단 알림에서 액션 버튼 사용하기")
      builder.setContentText("확인 버튼 클릭하기")
      builder.setAutoCancel(true)

//      안드로이드 시스템에서 대신 동작할 내용 입력
      val okIntent = Intent(this, ActionReceiver::class.java).apply {
        action = "OK"
      }

//      안드로이드 시스템에 이벤트 동작 요청
      val okPendingIntent = PendingIntent.getBroadcast(this, 0, okIntent, PendingIntent.FLAG_IMMUTABLE)

//      상단 알림 객체에 이벤트 동작 요청 객체를 추가
      builder.addAction(android.R.drawable.ic_menu_call, "확인", okPendingIntent)

      val cancelIntent = Intent(this, ActionReceiver::class.java).apply { action = "CANCEL" }
      val cancelPendingIntent = PendingIntent.getBroadcast(this, 1, cancelIntent, PendingIntent.FLAG_IMMUTABLE)
      builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "취소", cancelPendingIntent)

      val moreIntent = Intent(this, ActionReceiver::class.java).apply { action = "MORE" }
      val morePendingIntent = PendingIntent.getBroadcast(this, 2, moreIntent, PendingIntent.FLAG_IMMUTABLE)
      builder.addAction(android.R.drawable.ic_menu_info_details, "자세히 보기", morePendingIntent)

//      안드로이드 시스템의 알림에 등록
      manager.notify(12, builder.build())
    }

    binding.btnNotificationRemote.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val builder: NotificationCompat.Builder

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "원격 입력 채널 ID"
        val channelName = "원격 메시지 입력"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "원격 입력으로 메시지 전달하기"
        channel.setShowBadge(true)

        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()

        channel.setSound(uri, audioAttributes)
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

        manager.createNotificationChannel(channel)

        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
      builder.setWhen(System.currentTimeMillis())
      builder.setContentTitle("알림창 원격 메시지 입력 사용하기")
      builder.setContentText("알림창에서 바로 전달할 메시지를 입력할 수 있음")
      builder.setAutoCancel(true)


//      원격 메시지 입력을 위한 부분

//      원격 입력 시 사용할 키, 원격 입력을 위해서 사용하는 사용자 브로드캐스트 리시버에서 사용함
      val receiverKeyName = "fullstack503-receiver"
//      RemoteInput 객체를 receiverKeyName로 생성함
      val remoteInput: RemoteInput = RemoteInput.Builder(receiverKeyName).run {
        setLabel("응답")
        build()
      }

//      액션 버튼과 동일하게 Intent를 생성하고 백그라운드에서 동작할 사용자 브로드캐스트 리시버를 등록함
      val receiverIntent = Intent(this, RemoteInputReceiver::class.java)
//      액션 버튼과 동일하게 PendingIntent를 getBroadcast()를 사용하여 생성
      val receiverPendingIntent = PendingIntent.getBroadcast(this, 100, receiverIntent, PendingIntent.FLAG_MUTABLE)

//      NotificationCompat에 addAction() 을 사용하여 생성한 PendingIntent를 추가
      builder.addAction(
        NotificationCompat.Action.Builder(android.R.drawable.ic_menu_send, "응답", receiverPendingIntent)
          .addRemoteInput(remoteInput).build()
      )

      manager.notify(13, builder.build())
    }

    binding.btnNotificationProgress.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val builder: NotificationCompat.Builder

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "프로그레스바 채널 ID"
        val channelName = "프로그레스바 이름"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "프로그레스바 사용하기"
        channel.setShowBadge(true)

        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()

        channel.setSound(uri, audioAttributes)
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

        manager.createNotificationChannel(channel)

        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
      builder.setWhen(System.currentTimeMillis())
      builder.setContentTitle("파일 다운로드 중 ...")
//      자동 닫기를 막기 위해서 setAutoCancel(), setOngoing()을 설정
      builder.setAutoCancel(false)
      builder.setOngoing(true)

//      상단 알림에 프로그레스바 출력
//      setProgress() : 상단 알림에 프로그레스바를 출력하기 위한 메소드
//      첫번째 매개변수 : 프로그레스바의 최대값
//      두번째 매개변수 : 프로그레스바의 현재값
//      프로그레스바가 움직이려면 스레드를 사용하여 두번째 매개변수를 계속 변경시켜 사용
//      builder.setProgress(100, 50, false)

//      스레드로 for 문을 사용하여 프로그레스바의 값을 변경
      Thread {
        for (i in 1..100 step 10) {
          builder.setProgress(100, i, false)
          manager.notify(14, builder.build())
//          0.5초 동안 for문 진행을 멈춤
          SystemClock.sleep(500)
        }

//        프로그레스바가 for 문을 통하여 값이 100 이상이 되면 상단 알림의 설정을 변경
        builder.setContentText("다운로드 완료!!")
        builder.setProgress(0, 0, false)
//        상단 알림 자동 닫기로 설정
        builder.setOngoing(false)
        builder.setAutoCancel(true)

        manager.notify(14, builder.build())
      }.start()
    }

    binding.btnNotificationStyle.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val builder: NotificationCompat.Builder

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "알림 스타일 채널 ID"
        val channelName = "알림 스타일 이름"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "알림 스타일 변경하기"
        channel.setShowBadge(true)

        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()

        channel.setSound(uri, audioAttributes)
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

        manager.createNotificationChannel(channel)

        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
      builder.setWhen(System.currentTimeMillis())
      builder.setContentTitle("상단 알림 스타일 변경하기")
      builder.setContentText("상단 알림에 여러가지 스타일 적용")
      builder.setAutoCancel(true)

//      상단 알림에 스타일을 적용 시 총 4가지 방식의 스타일을 적용할 수 있음
//      setStyle() 에 스타일 객체를 매개변수 사용하여 입력

//      큰 이미지 스타일
//      val option = BitmapFactory.Options().apply {
//        inSampleSize = 2
//      }
//
//      val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.cat03, option)
      
//      NotificationCompat의 BigPictureStyle 객체를 생성하고 사용할 이미지를 설정
//      val bigPictureStyle = NotificationCompat.BigPictureStyle()
//        .bigPicture(bigPicture)

//      생성된 NotificationCompat의 BigPictureStyle 객체를 builder의 setStyle() 를 사용하여 적용
//      builder.setStyle(bigPictureStyle)
      
//      긴 텍스트 스타일
//      NotificationCompat의 BigTextStyle() 객체를 생성하고 사용할 텍스트를 설정
//      생성된 NotificationCompat의 BigTextStyle() 객체를 builder의 setStyle() 를 사용하여 적용
//      val bigTextStyle = NotificationCompat.BigTextStyle().bigText(resources.getString(R.string.long_text))
//
//      builder.setStyle(bigTextStyle)

//      박스 스타일
//      NotificationCompat의 InboxStyle()에 addLine() 를 사용하여 목록을 추가
//      생성된 NotificationCompat의 InBoxStyle() 객체를 builder의 setStyle() 를 사용하여 적용
//      val inBoxStyle = NotificationCompat.InboxStyle()
//        .addLine("1과목 : html, css, javascript")
//        .addLine("2과목 : java")
//        .addLine("3과목 : Database")
//        .addLine("4과목 : JSP")
//        .addLine("5과목 : Spring")
//
//      builder.setStyle(inBoxStyle)

//      메시지 스타일
//      NotificationCompat의 MessagingStyle()에 Message() 를 사용하여 메시지를 추가
//      생성된 NotificationCompat의 MessagingStyle() 객체를 builder의 setStyle() 를 사용하여 적용

//      채팅창 형태이므로 사용자 객체를 생성함
      val sender1: Person = Person.Builder()
        .setName("사용자 1")
        .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher))
        .build()

      val sender2: Person = Person.Builder()
        .setName("사용자 2")
        .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher_round))
        .build()

      val msg1 = NotificationCompat.MessagingStyle.Message("안녕하세요", System.currentTimeMillis(), sender1)
      val msg2 = NotificationCompat.MessagingStyle.Message("반갑습니다.", System.currentTimeMillis(), sender2)

      val messageStyle = NotificationCompat.MessagingStyle(sender1)
        .addMessage(msg1)
        .addMessage(msg2)

      builder.setStyle(messageStyle)

      manager.notify(15, builder.build())
    }
  }


//  어노테이션을 사용하여 버전 호환성 처리
  @RequiresApi(Build.VERSION_CODES.R)
  private fun toastCallback() {
    val toast = Toast.makeText(this, "토스트 메시지 콜백 기능 사용하기", Toast.LENGTH_SHORT)
//      오류 발생, Toast 메시지의 callback 기능이 API 30 부터 제공되지만, 현재 프로젝트의 최소 설치 가능 버전을 API 29 버전부터 시작하기 때문에 호환성 문제가 발생할 수 있음
//      호환성 문제 해결 방법은 2가지
//      1. 어노테이션을 사용하여 사용할 수 있는 API 버전을 설정
//      2. if 문을 사용하여 지정한 버전 이상일 경우에만 실행
    
//  if 문을 사용하여 버전 호환성 처리
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      toast.addCallback(
        object: Toast.Callback() {
//          토스트 메시지가 출력되었다가 사라질때 자동 동작
          override fun onToastHidden() {
            super.onToastHidden()
            Log.d("fullstack503", "토스트 메시지 숨김")
          }

//          토스트 메시지가 출력될 때 자동 동작
          override fun onToastShown() {
            super.onToastShown()
            Log.d("fullstack503", "토스트 메시지 출력")
          }
        }
      )
    }
    toast.show()
  }
}













