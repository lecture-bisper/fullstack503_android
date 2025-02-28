package bitc.fullstack.app_20250228

import android.provider.ContactsContract.Data

//  Data Class : 코틀린에서 새로 추가된 클래스
//  코틀린에서 VO 혹은 DTO 클래스를 만들어 사용할 때 사용하는 클래스
//  특정 기능을 가지고 있는 것이 아니라 여러가지 형태의 데이터를 저장하기 위해서 사용하는 클래스
//  Data Class 의 객체를 서로 비교 시 객체에 저장된 데이터를 비교함

//  코틀린의 일반 클래스 선언
class NonDataClass(val name: String, val email: String, val age: Int) {}

//  Data 클래스 선언
data class DataClass(var name: String, var email: String, var age: Int) {}

fun main() {
  println("\n----- 데이터 클래스 -----\n")

  println("----- 일반 클래스를 사용하여 객체 생성 후 비교 -----")
//  일반 클래스를 사용하여 객체를 생성
//  각 객체에 동일한 데이터를 저장함
  val ndata1 = NonDataClass("아이유", "iu@bitc.ac.kr", 31)
  val ndata2 = NonDataClass("아이유", "iu@bitc.ac.kr", 31)

  println("ndata1 : ${ndata1.name}, ${ndata1.email}, ${ndata1.age}")
  println("ndata2 : ${ndata2.name}, ${ndata2.email}, ${ndata2.age}")

//  일반 클래스를 사용하여 생성한 객체를 비교 (주소를 비교)
  var result: Boolean = ndata1 == ndata2
  println("ndata1 과 ndata2 의 비교 : $result")

  println("----- Data 클래스를 사용하여 객체 생성 후 비교 -----")

//  Data 클래스를 사용하여 객체 생성
  val data1 = DataClass("아이유", "iu@bitc.ac.kr", 31)
  val data2 = DataClass("아이유", "iu@bitc.ac.kr", 31)

  println("data1 : ${data1.name}, ${data1.email}, ${data1.age}")
  println("data2 : ${data2.name}, ${data2.email}, ${data2.age}")

//  Data 클래스의 객체를 비교 (저장된 데이터를 비교함)
  result = data1 == data2
  println("data1 과 data2 의 비교 : $result")

  println()

//  equals() : 서로의 데이터가 같은지 아닌지를 확인하는 메소드
  result = ndata1.equals(ndata2)
  println("ndata1 과 ndata2 의 비교 : $result")
  result = data1.equals(data2)
  println("data1 과 data2 의 비교 : $result")

  println()

//  toString() : 객체가 가지고 있는 데이터를 출력하는 메소드
  println("일반 클래스의 객체 출력 : ${ndata1.toString()}")
  println("Data 클래스의 객체 출력 : $${data1.toString()}")


//  object 클래스 : 코틀린에서 제공하는 익명 클래스
//  익명 클래스 내부에서 연산을 진행함
//  이름이 없기 때문에 1회용으로 사용됨
  
//  사용법
//  val 변수명 = object {
//    구현한 익명 클래스의 멤버...
//  }
}











