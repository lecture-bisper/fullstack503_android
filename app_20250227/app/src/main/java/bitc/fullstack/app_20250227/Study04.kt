package bitc.fullstack.app_20250227

fun main() {

  println("----- 반복문 사용하기 -----\n")
  println("----- for 문 -----")

//  코틀린의 for문은 자바의 향상된 for문과 동일함
//  in : 지정한 배열 혹은 리스트에서 데이터를 하나씩 출력하면서 반복
//  .. : 지정한 시작 숫자부터 종료 숫자까지 1씩 증가하면서 반복
//  until : 지정한 시작 숫자부터 종료 숫자까지 1씩 증가하면서 반복, 종료 숫자는 포함하지 않음
//  step : 반복 시 증가시킬 크기를 설정
//  downTo 반복 시 숫자가 감소함, 감소할 크기를 설정

//  기본 for문 사용법
  val list1 = listOf(10, 20, 30, 40, 50)

  print("리스트 list1 의 값 출력 : ")
  for (i in list1) {
    print("$i, ")
  }

  println("\n----- in  ..  사용 -----")
  var sum1: Int = 0

  for (i in 1..10) {
    sum1 += i
    println("i : $i, sum1 : $sum1")
  }

  println("----- in until 사용 -----")
  sum1 = 0
  for (i in 1 until 10) {
    sum1 += i
    println("i : $i, sum1 : $sum1")
  }

  println("----- in step 사용 -----")
  for (i in 0 .. 10 step 2) {
    println("i : $i")
  }

  println("----- in downTo 사용 -----")
  for (i in 10 downTo 0) {
    println("i : $i")
  }

  println("----- in downTo step 사용 -----")
  for (i in 10 downTo 0 step 2) {
    println("i : $i")
  }

  println("\n ----- indices / withIndex() 사용 -----\n")
//  indices : 배열과 같은 컬렉션이 가지고 있는 index를 출력
//  withIndex() : 배열과 같은 컬렉션이 가지고 있는 index와 value를 동시에 출력

  val arrInt = arrayOf(10, 20, 30, 40, 50)

  for (i in arrInt) {
    println("arrInt가 가지고 있는 값 : $i")
  }

  println()

//  indices를 사용 시 for문 안에 있는 변수 i에 저장되는 데이터가 지정한 배열의 index 임
  for (i in arrInt.indices) {
    println("arrInt[$i] : ${arrInt[i]}")
  }

  println()

//  withIndex()를 사용 시 for 문 안에 있는 각각의 변수 index, value에 실제 배열의 index 번호와 해당 index에 저장된 value를 출력함
  for ((index, value) in arrInt.withIndex()) {
    println("index : $index, value : $value")
  }
  
  println("\n----- while문 -----\n")

//  while 문은 자바의 while문과 동일함
  var x = 1
  var total = 0

  while (x < 11) {
    total += x
    println("x : $x, total : $total")
    x++
  }
  
//  문제 2) 코틀린의 for문을 사용하여 구구단을 모두 출력하는 프로그램을 작성하세요
//  파일명 : Test2.kt
//  2단 ~ 9단까지 출력
//  출력 형태 :
//  --- 2단 ---
//  2 * 1 = 2
//  ...
//  2 * 9 = 18
//  --- 3단 ---
//  ...
//  9 * 9 = 81

}














