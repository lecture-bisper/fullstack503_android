package bitc.fullstack.app_20250228

//  클래스 상속
//  코틀린의 클래스도 자바와 동일하게 클래스 상속이 가능함
//  부모 클래스에 open 키워드를 추가하여 사용
//  상속받는 자식 클래스에 extends 대신 ' : ' 기호를 사용
//  자식 클래스는 상속 관계에 있는 부모 클래스의 생성자를 반드시 호출해야 함
//  super() : 자바의 super()와 동일하게 부모의 생성자를 호출하는 명령

//  부모 클래스로 사용되는 클래스
open class Super {
}

//  부모 클래스 Super 를 상속받아 사용하는 자식 클래스 Sub
class Sub : Super {
//  자식 클래스의 생성자가 super() 를 사용하여 부모 클래스의 생성자를 호출
  constructor(name: String): super() {
  }
}

//  부모 클래스로 사용할 수 있는 클래스
//  생성자에 매개변수와 초기화 코드가 없는 부모 클래스 Parent1 선언
open class Parent1 {
  var parentField1 = 100

  fun parentMethod1() {
    println("부모 클래스의 멤버 메소드")
  }
}

//  부모 클래스인 Parent1을 상속받아 사용하는 자식 클래스 Child1
//  자식 클래스인 Child1 도 생성자에 매개변수와 초기화 코드가 없음
//  상속받는 부모 클래스의 생성자를 직접 실행하여 호출
class Child11: Parent1() {
  var childField1 = 1000

  fun childMethod1() {
    println("자식 클래스 Child1의 멤버 메소드")
  }
}

//  부모 클래스 Parent1을 상속받는 자식 클래스 Child2 선언
//  자식 클래스 Child2는 매개변수가 있고 초기화 코드가 없는 주 생성자 보유
//  상속받는 부모 클래스의 기본 생성자를 직접 실행하여 호출
class Child12(var childField2: Int): Parent1() {
  var childField1: Int = 1000

  fun childMethod() {
    println("자식 클래스 Child2의 멤버 메소드")
  }
}

//  주 생성자에 매개변수가 있고 초기화 코드가 없는 부모 클래스
//  주 생성자의 매개변수에 var 를 사용하여 매개변수를 클래스의 필드로 사용
open class Parent2(var parentField2: String) {
  var parentField1 = 100

  fun parentMethod() {
    println("부모 클래스인 Parent2의 메소드 호출")
  }
}

//  부모 클래스 Parent2를 상속받아 사용하는 자식 클래스 Child21
//  자식 클래스의 생성자는 매개변수와 초기화 코드가 없음
//  상속받는 부모 클래스의 매개변수가 있는 생성자를 직접 실행하여 호출
class Child21: Parent2("아이유") {
  var childField1 = 1000

  fun childMethod() {
    println("자식 클래스 Child21의 메소드 호출")
  }
}

//  부모 클래스 Parent2를 상속받아 사용하는 자식 클래스 Child22
//  자식 클래스의 주 생성자는 매개변수를 가지고 있음
//  부모 클래스의 생성자를 직접 실행하여 호출
//  자식 클래스의 생성자에 사용된 매개변수를 부모 클래스의 생성자 호출 시 사용함
class Child22(name: String) : Parent2(name) {
  var childField1 = 1000

  fun childMethod() {
    println("자식 클래스 Child22의 메소드 호출")
  }
}

//  부모 클래스 Parent2 를 상속받아 사용하는 자식 클래스 Child23
//  자식 클래스 Child22와 동일한 형태이나 보조 생성자를 사용한 방식
//  자식 클래스의 보조 생성자에서 부모 클래스의 생성자를 super()로 호출
class Child23: Parent2 {
  var childField1 = 1000

  constructor(name: String) : super(name) {
  }

  fun childMethod() {
    println("자식 클래스 Child23의 메소드 호출")
  }
}

//  오버라이딩 : 부모 클래스의 멤버를 자식 클래스가 상속받아 그 내용을 수정하여 사용하는 것
//  코틀린에서는 오버라이딩 가능한 부모의 멤버에 open 키워드를 추가함
//  자식 클래스에서는 오버라이딩할 멤버에 override 키워드를 추가함

//  부모 클래스인 Parent3
//  open 키워드를 사용하여 다른 클래스가 상속받을 수 있도록 함
open class Parent3 {
//  자식 클래스에서 오버라이딩할 수 있는 멤버는 open 키워드를 추가해야함
  open var someData = 300
  open var someData2 = 400

  open fun someMethod() {
    println("부모 클래스의 메소드 호출 : $someData")
  }
}

class Child3: Parent3() {
//  오버라이딩 할 멤버에 override 키워드를 추가해야 함
//  부모 클래스에서 상속받은 필드 someData 와 같은 이름의 필드가 존재하기 때문에 오류가 발생함
  override var someData = 3000

//  부모 클래스에서 상속받은 메소드 someMethod 와 같은 메소드가 존재하기 때문에 오류가 발생함
  override fun someMethod() {
    println("자식 클래스의 메소드 호출 : $someData")
  }
}

//  접근제한자 : 코틀린에서는 접근제한자로 public, internal, protected, private 4가지를 제공함
//  자바와 동일하며, internal은 자바의 default 와 동일한 접근제한자임
//  protected 는 클래스에서 사용 시 자바와 동일하지만 코틀린에서는 파일의 최상위 멤버로 변수와 함수를 사용할 수 있기 때문에 변수와 함수에는 protected 를 사용할 수 없음
// 코틀린의 기본 접근제한자는 public, 접근제한자 생략 시 public으로 인식

open class Parent4 {
//  접근제한자 생략, 기본 접근제한자인 public 으로 동작
  var publicData = 10
//  protected를 사용하여 자식 클래스에서는 사용 가능
  protected var protectedData = 20
//  private을 사용하여 현재 클래스 내부에서만 사용 가능
  private var privateDate = 30
}

//  부모 클래스 Parent4를 상속받은 Child4
class Child4: Parent4() {
  fun childMethod() {
//    상속받은 필드, 접근제한자가 public 이므로 어디서나 사용 가능
    publicData++
//    상속받은 필드, 접근제한자가 protected 이므로 Parent4를 상속받은 Child4 내부에서는 사용 가능
    protectedData++
//    접근제한자가 private 이므로 상속받지 못함, 사용 불가
//    privateData++
  }
}

fun main() {
  println("----- 클래스 상속 -----\n")

  val child11 = Child11()
  println(child11.parentField1)
  println(child11.childField1)
  child11.parentMethod1()
  child11.childMethod1()

  println()

  val child12 = Child12(2000)
  println(child12.childField1)
  println(child12.childField2)
  println(child12.parentField1)
  child12.parentMethod1()
  child12.childMethod()

  println()

  val child21 = Child21()
  println(child21.parentField1)
  println(child21.parentField2)
  println(child21.childField1)
  child21.parentMethod()
  child21.childMethod()

  println()

  val child22 = Child22("아이유")
  println(child22.parentField1)
  println(child22.parentField2)
  println(child22.childField1)
  child22.parentMethod()
  child22.childMethod()

  println()

  val child23 = Child23("아이유")
  println(child23.parentField1)
  println(child23.parentField2)
  println(child23.childField1)
  child23.parentMethod()
  child23.childMethod()

  println("\n----- 오버라이딩 -----\n")

  val child3 = Child3()
//  상속받은 필드를 오버라이딩하여 사용
  println(child3.someData)
//  상속받은 메소드를 오버라이딩하여 사용
  child3.someMethod()
//  부모 클래스에서 상속받은 필드를 그대로 사용
  println(child3.someData2)

  println("\n----- 접근제한자 -----\n")

//  Child4 클래스 타입의 객체 생성
  val child4 = Child4()
//  메소드 실행
  child4.childMethod()
//  접근제한자가 public 이므로 어디서나 사용 가능
  child4.publicData++
//  접근제한자가 protected 이므로 Parent4를 상속받은 Child4 클래스의 내부에서만 사용 가능
//  child4.protectedData++
//  접근제한자가 private 이므로 Parent4 내부에서만 사용 가능
//  child4.privateDate++
}













