package bitc.fullstack.app_202550317.data

import com.google.gson.annotations.SerializedName

data class Kobis(
//  @SerializedName : 연동되는 Json 데이터의 키명과 코틀린 클래스의 필드 명이 다를 경우 서로 일치시키기 위해서 사용하는 어노테이션, json 데이터의 키명과 코틀린 클래스의 필드명이 같을 경우 사용하지 않아도 상관없음
  @SerializedName("boxOfficeResult")
  var boxOfficeResult: BoxOfficeResult? = null
)













