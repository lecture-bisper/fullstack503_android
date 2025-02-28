package bitc.fullstack.app_20250228

import android.view.View

//  함수 타입 선언 : 람다 함수를 사용 시 람다 함수를 호출하기 위해서 변수에 람다 함수를 저장하여 사용함
//  해당 변수에 저장할 수 있는 함수의 형태를 선언하는 것을 뜻함

//  코틀린에서 일반적인 함수 선언
fun function1(num1: Int, num2: Int): Int {
  var result: Int = 0
  result = num1 + num2
  return result
}

//  위의 함수를 람다함수로 변환
//  변수 function2 는 데이터 타입을 자동 추론함
val function2 = {num1: Int, num2: Int ->
  var result = 0
  result = num1 + num2
  result
}

//  변수를 먼저 선언하고, 나중에 람다함수를 저장할 예정
//  저장할 람다함수의 형태를 지정할 수 있음
//  변수 function21은 저장할 데이터 타입을 지정해 놓음
var function21: (Int, Int) -> Int = {num1: Int, num2: Int ->
  var result = 0
  result = num1 + num2
  result
}


//  타입 별칭 : 람다 함수를 저장할 변수에 적용할 데이터 타입을 미리 지정해 놓고 사용하는 방식
//  typealias 키워드를 사용하여 설정
//  사용법
//  typealias 별칭명 = 람다 함수 타입
//  val 변수명: 별칭명 = 람다함수

//  변수에 일반 함수를 (익명함수) 저장하기
val function3 = fun (num1: Int, num2: Int): Int {
  var result = 0
  result = num1 + num2
  return result
}

val function31: (Int, Int) -> Int = fun (num1: Int, num2: Int): Int {
  var result = 0
  result = num1 + num2
  return result
}

//  타입 별칭 선언
typealias MyFuncType = (Int, Int) -> Int

//  타입 별칭을 사용한 변수에 람다 함수 저장
val function41: MyFuncType = {num1: Int, num2: Int ->
  var result = 0
  result = num1 + num2
  result
}

//  타임 별칭을 사용한 변수에 일반 함수 저장
val function42: MyFuncType = fun (num1: Int, num2: Int): Int {
  val result = num1 + num2
  return result
}

//  람다함수를 사용하는 이유
//  1. 소스코드가 짧아짐
//  2. 안드로이드에서 람다 함수를 많이 사용


//  람다 함수 없이 버튼의 클릭 이벤트 발생 소스
//  button.setOnClickListener(new OnClickListener() {
//    @Override
//    public void onClick(View view) {
//      클릭시 동작할 소스코드
//    }
//  })

//  람다 함수를 사용한 버튼의 클릭 이벤트 소스
//  button.setOnClickListener {
//    클릭시 동작할 소스코드
//  }


//  널 안정성 : 코틀린에서는 모든 변수가 객체이기 때문에 null 이 저장될 수 있는 가능성이 존재함
//  코틀린에서 널에 대한 안전한 사용을 위해서 기본 변수에는 null을 저장할 수 없도록 설정해 놓음
//  ?   - 데이터 타입뒤에 ? 사용 시 해당 변수에 null을 저장할 수 있음
//  ?.  - 일반 변수는 null 을 저장할 수 없기 때문에 null pointer Exception 을 걱정할 필요가 없음, ?를 사용한 null 허용 변수의 경우 null 저장할 수 있기 때문에 null pointer Exception 이 발생할 수 있음, null 허용 변수를 안전하게 실행하기 위한 명령어가 ?. 임
//  ?:  - null 허용 변수 사용 시 기본값을 설정하여 사용, 해당 변수에 값이 존재 시 해당 값을 사용하고, 해당 변수의 값이 null 일 경우 지정한 기본값을 사용
//  !!  - 지정한 객체가 null 일 경우 예외를 발생시키는 연산자


//  고차 함수 : 코틀린에서 함수의 매개변수로 함수를 전달하는 것을 고차함수라 함

//  함수 hofFun() 선언
//  매개변수 arg 를 선언, 타입을 (Int) -> Boolean 을 설정
//  함수 hofFun() 의 반환 타입으로 람다함수로 선언함
//  람다 함수의 마지막 라인이 값일 경우 해당 값을 리턴함
fun hofFun(arg: (Int) -> Boolean): () -> String {
//  val arg = {no: Int -> no > 0}

//  매개변수로 받아온 람다함수를 if 문의 조건식에 사용하고 그 결과를
//  변수 result 에 저장 (현재 저장된 값은 "valid")
  val result = if (arg(10)) {
    "valid"
  } else {
    "invalid"
  }

//  반환 타입인 매개변수가 없고 반환값이 String 타입인 람다함수를 반환함
//  매개변수가 없으므로 -> 도 생략
  return { "hofHun result : $result" }
}

fun main() {
//  변수 result 선언
//  변수 result에 함수 hofFun() 을 저장
//  함수 hofFun의 매개변수로 람다함수를 전달
//  람다함수는 매개변수 1개와 연산 및 반환값 (Boolean 타입) 이 있는 함수
  val result = hofFun({no -> no > 0})
  
//  변수 result에 저장된 함수를 호출하여 결과를 출력
  println(result())


  println("\n ----- null 안전성 -----\n")

//  변수 result2 는 데이터 타입에 ? 가 없기 때문에 null을 사용할 수 없음
  var result2: String = "안녕하세요"
  println(result2.length)

//  변수 result2에 빈 문자열 저장
  result2 = ""
  println(result2.length)

//  변수 result2에 null 을 저장할 경우 오류가 발생, ? 연산자를 사용하지 않은 null 불허용 변수이기 때문임
//  result2 = null

//  변수 result3의 데이터 타입에 ? 를 사용하여 null 허용 변수로 선언
  var result3: String? = "안녕하세요"
//  변수 result3은 null 허용 변수이기 때문에 안전하게 실행하기 위해서 ?. 으로 실행
  println(result3?.length)

//  변수 result3에 빈 문자열 저장
  result3 = ""
  println(result3?.length)

//  변수 result3은 null 허용 변수 이기 때문에 null 을 저장할 수 있음
  result3 = null
//  null 허용 변수를 안전하게 실행하는 ?. 을 사용했기 때문에 저장된 값이 null 이면 null을 출력, 값이 있으면 해당 값을 출력
  println(result3?.length)

  println("\n----- ?: -----")

//  변수 data에 ? 를 사용하여 null 허용 변수로 선언
  var data: String? = "안녕하세요"
  println("변수 data : $data")

//  변수 data에 문자열 저장
  data = "hello world!!"
//  ?: (엘비스 연산자) 를 사용하여 기본값 설정
  println("변수 data : $data, 길이 : ${data.length ?: 0}")

//  변수 data에 null 저장
  data = null
//  ?: (엘비스 연산자) 를 사용하여 기본값 설정, 결과가 null 경우 기본값이 출력
  println("변수 data : $data, 길이 : ${data?.length ?: 0}")

  println("\n----- !! 사용 -----")

//  함수 some의 매개변수로 문자열을 전달함
  println(some("hello world!!"))
//  함수 some의 매개변수로 null을 전달
  println(some(null))
}

//  매개변수로 null을 허용하는 문자열을 받음
//  반환값으로 정수를 출력
fun some(data: String?) : Int {
//  !!는 null 일 경우 예외 발생
  return data!!.length
}

















