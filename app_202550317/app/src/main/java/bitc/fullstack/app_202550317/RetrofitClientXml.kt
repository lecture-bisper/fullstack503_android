package bitc.fullstack.app_202550317

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.create

//  Retrofit 라이브러리를 사용하여 실제 지정한 url와 통신을 하기 위한 Retrofit 설정 객체
//  class 가 아닌 object 인 이유는 코틀린을 통한 싱글톤 방식으로 단 하나의 객체만 생성하여 계속 활용하기 위함, 컴패니언 클래스를 사용하는 방법도 있음
object RetrofitClientXml {

  private val BASE_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

  val instance: KobisDailyBoxOfficeAPI by lazy {
//    Retrofit 객체를 Builder 패턴을 사용하여 객체 생성
    Retrofit.Builder()
//      BaseUrl 설정
      .baseUrl(BASE_URL)
//      서버에서 받아오는 데이터를 컨버팅할 클래스 설정 (build.gradle.kts 에 등록한 라이브러리)
      .addConverterFactory(SimpleXmlConverterFactory.create())
      .build()
      .create(KobisDailyBoxOfficeAPI::class.java)
  }

}














