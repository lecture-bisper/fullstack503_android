package bitc.fullstack.app_202550317

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.fullstack.app_202550317.data.BoxOfficeResult
import bitc.fullstack.app_202550317.data.Kobis
import bitc.fullstack.app_202550317.databinding.ActivityHttpConnectionBinding
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call

class HttpConnectionActivity : AppCompatActivity() {

  private val binding: ActivityHttpConnectionBinding by lazy {
    ActivityHttpConnectionBinding.inflate(layoutInflater)
  }

  private lateinit var queue: RequestQueue

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    Volley 라이브러리가 사용할 저장소 선언
    queue = Volley.newRequestQueue(this)

    initEventListener()
  }

  private fun initEventListener() {
    binding.btnVolleyRequestString.setOnClickListener {
//      Volley 로 접속할 url 가져오기
      val url = binding.etServerUrl.text.toString()

//      StringRequest() 를 사용 GET 방식으로 접속
      val stringRequest = StringRequest(
        Request.Method.GET,
        url,
//        통신 성공 시 동작
        Response.Listener<String> {
          Log.d("fullstack503", "서버에서 데이터 받아옴")
          binding.tvResult.text = it
          Log.d("fullstack503", it)
        },
//        통신 실패 시 동작
        Response.ErrorListener { error -> Log.d("fullstack503", "error : $error") }
      )

//      실제 Volley 가 동작할 Queue 에 생성된 StringRequest 객체를 추가
      queue.add(stringRequest)
    }

    binding.btnVolleyRequestJson.setOnClickListener {
//      Volley 가 데이터를 파싱할 서버의 주소 가져오기
      val url = binding.etServerUrl.text.toString()

//      Json 데이터 파싱
//      JsonObjectRequest - 서버에서 전달받는 데이터가 Json Object 타입일 경우 사용, {} 로 시작
//      JsonArrayRequest - 서버에서 전달받는 데이터가 Json Array 타입일 경우 사용, [] 로 시작
      val jsonRequest = JsonObjectRequest(
//        접속 방식 설정
        Request.Method.GET,
//        서버 URL 설정
        url,
        null,
//        통신이 성공했을 경우
        {
          Log.d("fullstack503", "Json 방식으로 데이터 가져오기 성공!!")
          Log.d("fullstack503", "$it")

//          Json 데이터 파싱을 위해서 Gson 객체 생성
          val gson = Gson()
//          Gson을 이용하여 Json 데이터를 파싱, Json 데이터와 연동될 최상위 클래스를 설정
          val kobis: Kobis = gson.fromJson(it.toString(), Kobis::class.java)
//          전체 데이터에서 필요한 부분만 가져오기
          val dailyBoxOfficeList = kobis.boxOfficeResult?.dailyBoxOfficeList

          for (item in dailyBoxOfficeList!!) {
            Log.d("fullstack503", "제목 : ${item.movieNm}")
          }
        },
//        통신이 실패했을 경우
        { error -> Log.d("fullstack503", "Json 방식으로 데이터 가져오기 오류\nError : $error")}
      )

      queue.add(jsonRequest)
    }

    binding.btnRetrofitRequestString.setOnClickListener {
//      Retrofit 객체 생성, 사용자가 지정한 인터페이스를 사용함
      val api = RetrofitClientXml.instance
//      사용자가 지정한 인터페이스의 메소드를 호출함
      val call = api.getDailyBoxOfficeXml(
        key = "c55013eadce1f0005fae142c556a228d",
        targetDt = "20250316"
      )

//      Retrofit 통신 결과를 받아옴
      call.enqueue(object: retrofit2.Callback<BoxOfficeResult> {
//        통신 성공 시
        override fun onResponse(call: Call<BoxOfficeResult>, res: retrofit2.Response<BoxOfficeResult>) {
          if (res.isSuccessful) {
//            서버에서 받아온 데이터를 출력
            val boxOfficeResult = res.body()
            val boxOfficeList = boxOfficeResult?.dailyBoxOfficeList

            for (item in boxOfficeList!!) {
              Log.d("fullstack503", "순위 : ${item.rank}, 영화명 : ${item.movieNm}, 관객수 : ${item.audiCnt}")
            }
          }
          else {
            Log.d("fullstack503", "응답 실패 : ${res.errorBody()?.string()}")
          }
        }

//        통신 실패 시
        override fun onFailure(call: Call<BoxOfficeResult>, t: Throwable) {
          Log.d("fullstack503", "서버 요청 실패 : ${t.message}")
        }
      })
    }

    binding.btnRetrofitRequestJson.setOnClickListener {
      val api = RetrofitClientJson.instance
      val call = api.getDailyBoxOfficeJson(
        key = "c55013eadce1f0005fae142c556a228d",
        targetDt = "20250316"
      )

      call.enqueue(object : retrofit2.Callback<Kobis> {
        override fun onResponse(call: Call<Kobis>, res: retrofit2.Response<Kobis>) {
          if (res.isSuccessful) {
            val kobisData = res.body()
            kobisData?.boxOfficeResult?.dailyBoxOfficeList?.forEach {
              Log.d("fullstack503", "순위 : ${it.rank}, 제목 : ${it.movieNm}, 관객수 : ${it.audiCnt}")
            }
          }
          else {
            Log.d("fullstack503", "응답 실패 : ${res.errorBody()?.string()}")
          }
        }

        override fun onFailure(call: Call<Kobis>, t: Throwable) {
          Log.d("fullstack503", "서버 요청 실패 : ${t.message}")
        }
      })
    }

    binding.btnUrlClear.setOnClickListener {
      binding.etServerUrl.setText("")
      binding.tvResult.text = ""
    }
  }
}











