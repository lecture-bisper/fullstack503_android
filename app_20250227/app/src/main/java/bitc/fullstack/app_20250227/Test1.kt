package bitc.fullstack.app_20250227

//  문제 1) Test1 파일을 생성하여 코틀린 문법으로 사칙 연산을 위한 함수 4개를 선언하고 실행하는 프로그램을 작성하세요
//  # add(매개변수만 있는 함수), sub(반환값만 있는 함수), multi(매개변수와 변환값이 모두 있는 함수), div(매개변수와 반환값이 모두 없는 함수)

fun add(num1: Int, num2: Int) {
  println("두 수의 덧셈은 ${num1 + num2}")
}

fun sub(): Int {
  val num1 = 10
  val num2 = 20
  return num1 - num2
}

fun multi(num1: Int, num2: Int): Int {
  return num1 * num2
}

fun div(): Unit {
  val num1 = 10
  val num2 = 20
  val result = num1 / num2
  println("두 수의 나눗셈은 $result")
}

fun main() {
  add(10, 20)
  var result = sub()
  println("두 수의 뺄셈은 $result")
  result = multi(10, 20)
  println("두 수의 곱셈은 $result")
  div()
}














