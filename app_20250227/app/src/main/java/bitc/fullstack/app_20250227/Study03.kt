package bitc.fullstack.app_20250227

fun main() {

  println("----- 조건문 사용하기 -----\n----- if 문 -----")
//  조건문 : 코틀린에서 조건문은 if문, when문 을 사용
//  if문은 자바의 if 문과 동일함
//  when문은 자바의 switch ~ case 문과 동일함
  var data1 = 10

//  if문은 기본적으로 자바의 if문과 동일하게 사용
  if (data1 > 0) {
    println("data1은 0보다 크다")
  }
  else {
    println("data1은 0보다 작거나 같다")
  }

//  elseif 문도 자바와 동일하게 사용
  if (data1 > 10) {
    println("data1은 10보다 크다")
  }
  else if (data1 > 0 && data1 <= 10) {
    println("data1은 0보다 크고 10보다 작거나 같다.")
  }
  else {
    println("data1은 0보다 작다")
  }

  println("----- if문을 변수에 대입하기 -----")
//  코틀린에서는 if 문을 변수에 대입할 수 있음
//  if문의 코드블럭의 가장 마지막 데이터를 변수에 저장
  var result = if (data1 > 0) {
    println("data1 은 0 보다 크다")
    true // 변수에 저장될 데이터
  }
  else {
    println("data1 은 0 보다 작거나 같다.")
    false // 변수에 저장될 데이터
  }

  println("result : $result")

  println("----- 자바에서 구현 시 -----")

  data1 = 10
  result = false

  if (data1 > 0) {
    println("data1은 0보다 크다")
    result = true
  }
  else {
    println("data1은 0보다 작거나 같다")
    result = false
  }

  println("result : $result")


  println("\n ----- when 사용하기 -----\n")

//  when은 기본적으로 switch ~ case 문과 동일함
//  switch ~ case 문에서 case 부분문 제거되고 바로 값을 입력하는 형태
//  ' : ' 대신 ' -> ' 를 사용하며, break 문도 제거됨
//  실행할 소스코드가 1 라인일 경우 {} 생략 가능
//  switch ~ case 문의 default 대신 else 를 사용함
//  코틀린의 if문과 동일하게 변수에 when 문을 대입할 수 있음

//  사용법
//  when (변수) {
//    값1 -> { 소스코드 }
//    값2 -> { 소스코드 }
//    ...
//    else -> { 소스코드 }

  var data2 = 20
  when (data2) {
    10 -> println("data2의 값은 10")
    20 -> println("data2의 값은 20")
    else -> {
      println("data2의 값은 10도 20도 아님")
    }
  }

//  문자열 비교도 가능함
  var data3 = "hello"
  when (data3) {
    "hello" -> { println("data3 의 값은 hello")}
    "world" -> println("data3 의 값은 world")
    else -> println("data3의 값은 hello 도 world 도 아님")
  }
  
//  is : 코틀린의 데이터 타입 확인 연산자, 지정한 데이터 타입이 맞을 경우 true 아니면 false 를 출력
//  값1, 값2 : when 문에서 지정한 값을 1개만 사용하는 것이 아니라 여러개를 지정할 때 사용 함, 자바의 switch ~ case 문에서 case 를 여러개 설정하고 break 문을 입력하지 않은 것과 동일한 형태
//  in 데이터묶음 : 데이터 묶음에서 데이터를 하나씩 꺼내어 사용하는 연산자
//  값1..값2 : 값1 ~ 값2 까지의 연속된 데이터를 의미하는 연산자
//    1..10  = 1 ~ 10 을 의미, 1부터 10까지의 숫자
  
  // 변수 data4 를 선언과 동시에 정수 10을 대입, 데이터 타입이 Any이기 때문에 모든 데이터를 다 저장할 수 있음
  var data4: Any = 10
  when (data4) {
    is String -> println("data4는 문자열 타입")
    20, 30 -> println("data4의 값은 20 혹은 30")
    in 1..10 -> println("data4의 값은 1 ~ 10까지의 숫자")
    else -> println("data4는 데이터가 없음")
  }

  println("----- 변수에 when 대입하기 -----")

//  when 문 내부에서 간단한 연산이 가능함
//  when 문의 결과를 변수에 저장하는 것이 가능함
  var data5 = 10
  var result1: String = when {
    data5 <= 0 -> "data5는 0보다 작거나 같다"
    data5 > 100 -> "data5는 100보다 크다"
    else -> {
      "data5에 해당하는 값이 없음"
    }
  }

  println("result1 : $result1")

  var data6: Any = 10
  result1 = when(data6) {
    is String -> {
      println("data6은 문자열 타입")
      "data6은 문자열"
    }
    20, 30 -> {
      println("data6은 20 이나 30")
      "data6 is 20 or 30"
    }
    in 1..10 -> {
      println("data6은 1 부터 10 까지의 숫자 중 하나")
      "data6 in 1..10"
    }
    else -> {
      println("data6에 해당하는 값이 없음")
      "data6 is not valid"
    }
  }

  println("result1 : $result1")

}














