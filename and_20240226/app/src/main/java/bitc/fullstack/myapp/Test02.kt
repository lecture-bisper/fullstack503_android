package bitc.fullstack.myapp

// Byte 타입, java 및 kotlin 의 정수형 기본 데이터 타입은 Int 타입
//  Byte 타입의 데이터를 표시할 경우 '0b' 를 사용
val a1: Byte = 0b00001011

// 자바와 동일하게 사용
val a2: Short = 123
val a3: Int = 123
//  자바와 동일하게 Long 타입은 기본 데이터 타입인 Int 타입과 구분하기 위해서 접미사로 L 을 사용함
val a4: Long = 10L

//  실수 타입의 기본 데이터 타입은 Double 타입
//  자바와 동일하게 Float 타입은 기본 데이터 타입인 Double 타입과 구분하기 위해서 접미사 F 를 사용함
val a5: Float = 10.0F
val a6: Double = 10.0

//  코틀린의 Char 타입은 자바와 달리 정수 타입과 호환되지 않음
val a7: Char = 'A'

//  코틀린의 String 타입의 자바와 동일하게 사용
val a8: String = "Hello World!!"

fun main() {

//  템플릿 문자열 : 일반 문자열과 변수를 함께 사용 시 문자열 연결 연산자 없이 사용하는 방식
//  문자열 안에서 변수 사용 시 '$변수명' 형태로 사용
//  간단한 연산도 가능
//  연산 시 $뒤에 {} 를 사용하여 {} 안에서 연산을 진행
  println("a1 : $a1")
  println("a2 : $a2")
  println("a3 : $a3")
  println("a4 : $a4")
  println("a5 : $a5")
  println("a6 : $a6")
  println("a7 : $a7")
  println("a8 : $a8")
  println("변수 a3 + 123 = ${a3 + 123}") // 템플릿 문자열을 사용하여 연산 및 출력
  println("변수 a3 + 123 = " + (a3 + 123)) // 기존 방식으로 연산 및 출력

//  코틀린에서 Char 타입은 Java와 달리 정수 타입이 아니므로 정수와 비교 시 오류
//  println(a7 == 65)

//  코틀린에서 String 타입은 Java와 동일하게 사용
  var str1 = "Hello \n World!!"
  
//  코틀린에서 추가된 삼중따옴표 방식, 입력한 형태대로 그대로 사용 가능
//  trimIndent() : 문자열 앞의 공백을 삭제하는 명령어
  var str2 = """
        Hello
              World!!         
  """.trimIndent()
  
  println(str1)
  println(str2)


  println("\n ----- Any ----- \n")

//  Any : 코틀린의 모든 데이터 타입을 저장할 수 있는 데이터 타입, Java의 Object 타입과 같은 역할을 함
//  Any 타입의 변수에 정수 데이터 저장
  val data1: Any = 10
  println("data1 : $data1")
//  Any 타입의 변수에 문자열 데이터 저장
  val data2: Any = "Hello"
  println("data2 : ${data2}")
//  Any 타입의 변수에 클래스의 객체를 저장
  var data3: Any = Test01()
  println("data3 : ${data3}")
//  var 키워드롷 만들어진 변수에 Any 데이터 타입을 지정하여 해당 변수에는 여러가지 타입의 데이터를 모두 저장할 수 있음
  data3 = 10
  println("data3 = $data3")


  println("\n ----- Unit ----- \n")

//  Unit : 코틀린에서 해당 함수의 변환 값이 없다는 의미의 키워드, 자바의 void 키드와 같음
//  Unit 키워드를 생략하면 기본 설정으로 반환 타입이 Unit 가 적용됨
  some1()
  some2()


  println("\n ----- Nothing -----\n")
//  Nothing : null 만 저장할 수 있는 데이터 타입
//  함수나 메소드의 반환 타입으로 Nothing 을 사용할 경우 null과 예외만 반환

//  변수의 데이터 타입을 Nothing 으로 지정하여 null 만 저장
  var data4: Nothing? = null
//  데이터 타입을 Nothing 타입으로 지정한 변수에 null 이 아닌 다른 데이터를 저장하여 오류 발생
//  data4 = 100

//  널 허용 / 널 불허용 : 코틀린의 변수는 모든 변수가 객체이기 때문에 null 을 저장할 수 있는 가능성이 존재함
//  코틀린에서는 null 사용을 안전하게 하기 위해서 변수의 데이터 타입에 ' ? ' 을 사용하여 null 저장을 허용하거나 불허용함
//  데이터 타입에 ' ? ' 이 있으면 null 저장 가능
//  데이터 타입에 ' ? ' 이 없으면 null 저장 불가

  var data5: Int = 10
  var data6: Int? = 10

  println("data5 : $data5")
  println("data6 : $data6")

//  변수 data5 의 데이터 타입은 Int 이므로 null 저장 불가
//  data5 = null
//  변수 data6 의 데이터 타입은 Int? 이므로 null 저장 가능
  data6 = null


}

//  반환 타입이 없음을 나타내기 위해서 Unit 키워드를 지정함
fun some1(): Unit {
  println(10 + 20)
}

//  반환 타입이 없음을 나타내기 위해서 Unit 키워드를 생략함
//  반환값이 없을 경우 Unit 키워드를 생략해도 됨, 컴파일러가 자동으로 붙임
fun some2() {
  println(10 + 20)
}

//  반환 타입을 Nothing? 타입으로 설정하여 null 을 반환
fun some3(): Nothing? {
  return null
}

//  반환 타입의 Nothing 타입으로 설정하여 예외만 반환
fun some4(): Nothing {
  throw Exception()
}













