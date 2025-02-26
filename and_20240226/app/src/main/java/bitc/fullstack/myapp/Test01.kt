package bitc.fullstack.myapp

//  코틀린에서는 변수와 함수를 클래스의 멤버로 포함하지 않아도 사용이 가능함
//  코틀린에서는 소스코드의 마지막에 ' ; ' 을 생략함
//  코틀린 언어의 시작점은 main() 함수임

//  변수 : val, var 키워드를 사용하는 데이터를 저장하는 메모리 공간
//  코틀린의 변수는 저장된 데이터를 자동으로 확인하는 데이터 추론 방식을 사용
//  변수에 데이터 타입을 지정하고자 할 경우 변수명 뒤에 ' : 데이터 타입' 을 사용
//  코틀린에서 변수 선언 시 초기값을 설정하지 않고, 변수만 선언할 경우 데이터 타입을 반드시 지정해야 함
//  사용법
//  var data1 = 100
//  val data2 = 200
//  var data3 : Int = 300
//  val data4 : Int = 400

//  val : 초기값 설정 후 데이터를 변경할 수 없는 변수, 자바의 읽기 전용인 final 변수와 같음
//  var : 초기값 설중 후 데이터를 변경할 수 있는 변수, 자바의 일반적인 변수

val data1 = 100
var data2 = 200

fun main() {
  println("코틀린 프로그램 첫 실행")

  println("data1 : " + data1)
  println("data2 : " + data2)

//  val로 선언한 변수는 데이터를 수정할 수 없음
//  data1 = 1000
//  var로 선언한 변수는 데이터를 수정할 수 있음
  data2 = 2000

  println("수정 후 \ndata1 : " + data1)
  println("data2 : " + data2)

  var data3: Int = 300
  println("데이터 타입을 지정한 변수 data3 : " + data3)

//  데이터가 초기화된 변수는 다른 데이터 타입의 데이터를 저장할 수 없음
//  data3 = "문자열"

//  데이터 추론 방식으로 데이터 타입이 지정된 변수는 다른 데이터 타입의 데이터를 저장할 수 없음
  var data4 = 400
//  data4 = "문자열"

//  초기값을 설정하지 않고 변수만 미리 선언할 경우 데이터 타입을 필수적으로 입력
  var data5: Int
//  초기값이 없이 선언만 된 변수를 사용할 수 없음
//  println("초기값 없이 선언만한 변수 : " + data5)

  data5 = 500
  println("나중에 데이터를 저장한 data5 : " + data5)

//  초기값 없이 변수를 선언하여 오류
//  var data6


//  lateinit 실제 해당 변수를 사용할 때 데이터를 초기화하여 사용한다는 의미
//  기본 데이터 타입은 사용 불가(참조 타입의 변수만 사용 가능)

//  lateinit var data7: Int // 데이터 타입이 기본 타입인 int 이기 때문에 오류
//  lateinit val data8: String // 변수 생성 키워드가 val 이기 때문에 오류
  lateinit var data9: String // 정상 사용

//  by lazy {} 는 val, var 키워드 모두 사용 가능
//  by lazy {} 는 모든 데이터타입에서 사용 가능
//  by lazy {} 의 코드 블럭 안에서 간단한 연산이 가능
//  by lazy {} 의 코드 블럭 마지막에 존재하는 데이터를 해당 변수의 초기값으로 사용

//  by lazy {} 를 사용하여 데이터를 지연 초기화함
  val data10: Int by lazy {
    1000
  }

  println("\n ----- by lazy 로 지연 초기화한 변수 사용 ----- \n")
  println(data10 + 1000)

//  by lazy 를 지연 초기화 변수 선언
  val data11: Int by lazy {
//    해당 변수를 최초로 사용 시 함수처럼 코드 블럭의 내용을 실행
    println("변수 data11 은 지연초기화 됨")
//    가장 마지막의 값을 해당 변수의 데이터로 저장
    1000
  }

  val data12: Int by lazy {
    println("변수 data12에 데이터 저장")
    data11 + 500
  }

//  가장 먼저 실행
  println(" 변수 data11 보다 먼저 실행되는 부분...")
//  변수 data11을 최초로 사용
  println(data11 + 1000)
//  변수 data11에 이미 데이터가 저장되어 있으므로 기존 데이터를 그대로 사용
  println(data11 + 2000)

  println("data12 보다 먼저 실행")
  println(data12 + 500)
  println(data12 + 1000)

}

class Test01() {

}













