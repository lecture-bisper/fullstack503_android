package bitc.fullstack.app_20250227

fun main() {
  function1()
  function2()
  function3(10, 20)

  var result = function4()
  println("function4()에서 반환 받은 값 : $result")
  result = function5(10, 20)
  println("function5()에서 반환 받은 값 : $result")

  println("---------------")

  function6("아이유", 31, false)
  function6("아이유", 31)
  function7("유재석", true, 50)
//  function7("유재석", 50)

  println("----------------------")
//  함수 호출 시 매개변수의 이름을 직접 지정하여 데이터를 전달
//  호출할 매개변수의 선언부에 입력되어 있는 매개변수 명을 입력하여 데이터 전달
//  매개변수의 이름을 지정하여 데이터를 전달하기 때문에 매개변수의 순서가 상관없어짐
  function6(name = "아이유", age = 31, gender = false)

  println("----- 매개변수의 순서를 변경해서 호출 -----")
  function6(age = 31, name = "아이유", gender = false)

  println("----- 매개변수의 순서를 변경하고 기본값 사용하여 호출 -----")
  function6(age = 31, name = "아이유")
  function7(gender = true, name = "유재석", age = 50)

  println("----- 기본값이 설정된 매개변수가 중간에 있는 함수를 매개변수 이름을 지정해서 호출 -----")
  function7(age = 50, name = "유재석")

}

//  코틀린 함수 : 코틀린은 함수가 클래스에 포함되지 않고 사용할 수 있음
//  함수 선언 시 fun 키워드를 사용함

//  사용법
//  fun 함수명(매개변수: 데이터 타입) : 반환타입 {
//    함수 호출 시 실행할 소스코드
//    return 반환값
//  }

//  함수 선언의 기본 형태, 반환값이 없는 함수 선언
fun function1(): Unit {
  println("반환값과 매개변수가 없는 함수")
}

//  반환값이 없을 경우 Unit를 생략할 수 있음
fun function2() {
  println("반환 타입이 없을 경우 생략 가능함")
}

//  반환값이 없기 때문에 함수 뒤에 Unit 추가
//  매개변수 사용 시 var, val 키워드 미입력, 기본적으로 val로 적용
fun function3(num1: Int, num2: Int): Unit {
  println("반환값은 없고, 매개변수가 있는 함수")
  println("num1 : $num1, num2 : $num2")
//  매개변수로 전달받은 데이터는 val로 선언되어 있기 때문에 수정 불가
//  num1 = 100
}

fun function4(): Int {
  println("매개변수는 없고 반환값은 있는 함수")
  val num1 = 10
  val num2 = 20
  val result = num1 + num2
  return result
}

fun function5(num1: Int, num2: Int) : Int {
  println("매개변수와 반환값이 모두 있는 함수")
  val result = num1 + num2
  return result
}

//  함수의 매개변수에 기본값 설정
fun function6(name: String, age: Int, gender: Boolean = true) {
  println("함수의 매개변수에 기본값을 설정하여 사용하는 함수")

  if (gender) {
    println("이름 : $name\n나이 : $age\n성별 : 남성")
  }
  else {
    println("이름 : $name\n나이 : $age\n성별 : 여성")
  }
}

//  매개변수 기본값 사용 시 기본값이 설정된 매개변수는 가장 마지막에 입력
//  기본값이 있는 매개변수를 매개변수의 순서 중간에 설정 시 해당 함수를 호출할 경우 기본값을 가지고 있는 매개변수를 생략하면 매개변수에 전달하는 값의 위치를 알 수 없기 때문에 오류 발생
fun function7(name: String, gender: Boolean = true, age: Int) {
  if (gender) {
    println("이름 : $name\n나이 : $age\n성별 : 남성")
  }
  else {
    println("이름 : $name\n나이 : $age\n성별 : 여성")
  }
}











