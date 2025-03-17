package bitc.fullstack.app_202550317

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//  XML 방식과 JSON 방식을 동시에 사용 시 XML 파싱 시 사용하는 라이브러리와 Json 파싱 시 사용하는 라이브러리가 다르기 때문에 각각 따로 만드는 것이 좋음
object RetrofitClientJson {

  private val BASE_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

  val instance: KobisDailyBoxOfficeAPI by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
//      Json 파싱이기 때문에 Converter-Gson 에서 제공하는 GsonConverterFactory.create() 로 설정
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(KobisDailyBoxOfficeAPI::class.java)
  }
}














