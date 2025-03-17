package bitc.fullstack.app_202550317

import bitc.fullstack.app_202550317.data.BoxOfficeResult
import bitc.fullstack.app_202550317.data.Kobis
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//  Retrofit 라이브러리를 사용하여 서버와 통신 시 사용할 메소드 묶음
interface KobisDailyBoxOfficeAPI {

//  @GET(@POST, @PUT, @DELETE) : 서버와 통신 방식을 설정하는 어노테이션, () 안에 url 을 입력 시 서브 url로 동작하여 baseUrl + subUrl 이 되어 전체 주소가 생성됨
  
//  @GET 을 사용하여 get 방식으로 서버와 통신하고 baseUrl + 서브 주소인 searchDailyBoxOfficeList.xml 로 접속 설정
//  매개변수로 key 와 targetDt 를 사용함
  @GET("searchDailyBoxOfficeList.xml")
  fun getDailyBoxOfficeXml(
    @Query("key") key: String,
    @Query("targetDt") targetDt: String
  ): Call<BoxOfficeResult>
  
//  @Query : 전체 url에서 ? 뒤에 있는 쿼리 스트링의 변수와 값을 지정하는 어노테이션
  @GET("searchDailyBoxOfficeList.json")
  fun getDailyBoxOfficeJson(
    @Query("key") key: String,
    @Query("targetDt") targetDt: String
  ): Call<Kobis>

}














