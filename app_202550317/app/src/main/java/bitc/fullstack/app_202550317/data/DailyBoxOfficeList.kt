package bitc.fullstack.app_202550317.data

import com.google.gson.annotations.SerializedName

data class DailyBoxOfficeList(
  @SerializedName("rnum")
  var rnum: String? = null,

  @SerializedName("rank")
  var rank: String? = null,

  @SerializedName("rankInten")
  var rankInten: String? = null,

  @SerializedName("rankOldAndNew")
  var rankOldAndNew: String? = null,

  @SerializedName("movieCd")
  var movieCd: String? = null,

  @SerializedName("movieNm")
  var movieNm: String? = null,

  @SerializedName("openDt")
  var openDt: String? = null,
  
  @SerializedName("salesAmt")
  var salesAmt: String? = null,
  
  @SerializedName("salesShare")
  var salesShare: String? = null,

  @SerializedName("salesInten")
  var salesInten: String? = null,

  @SerializedName("salesChange")
  var salesChange: String? = null,

  @SerializedName("salesAcc")
  var salesAcc: String? = null,

  @SerializedName("audiCnt")
  var audiCnt: String? = null,

  @SerializedName("audiInten")
  var audiInten: String? = null,

  @SerializedName("audiChange")
  var audiChange: String? = null,

  @SerializedName("audiAcc")
  var audiAcc: String? = null,

  @SerializedName("scrnCnt")
  var scrnCnt: String? = null,

  @SerializedName("showCnt")
  var showCnt: String? = null
)













