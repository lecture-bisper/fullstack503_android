package bitc.fullstack.app_20250228

//  코틀린에서 클래스 선언
//  기본적으로 자바의 클래스 선언 방법과 동일함

//  빈 클래스 선언
class User1 {
}

//  빈 클래스의 경우 {} 생략 가능
class User2

//  클래스의 멤버는 자바와 동일하게 필드, 메소드, 생성자 임
class User {
  var name = "choi" // 필드

//  생성자, 보조 생성자
  constructor(name: String) {
    this.name = name
  }

//  메소드
  fun someFun() {
    println("name : $name")
  }

  class SomeClass {}
}

//  코틀린의 생성자는 2가지 종류가 존재함
//  코틀린의 생성자는 자바와 달리 이름이 constructor() 으로 지정되어 있음
//  주 생성자 : 생성자의 이름인 constructor 을 생략할 수 있음(주로 생략하여 사용), 생략 시 () 만 사용
//    클래스 명 뒤에 constructor() 혹은 () 만 붙여서 사용함
//    매개변수가 없는 주 생성자이며 실행되는 내용도 없을 경우 () 도 생략 가능함
//    주 생성자는 1개만 존재할 수 있음
//    주 생성자에 매개변수를 사용할 수 있음
//    주 생성자의 매개변수에 var, val 을 추가 시 해당 매개변수를 해당 클래스의 필드로 사용
//    주 생성자에서 필드의 초기화나 메소드 호출을 위한 init {} 코드블럭을 사용할 수 있음

//  보조 생성자 : 생성자의 이름이 constructor 이며, 이름을 생략할 수 없음
//    보조 생성자는 생성자 오버로딩이 가능함
//    자바의 생성자를 선언하는 것처럼 해당 클래스의 내부에 선언함
//    보조 생성자는 매개변수에 var, val 을 입력할 수 없음


//  주 생성자를 이름까지 모두 입력하여 사용한 클래스
class User4 constructor() {}

//  주 생성자의 이름을 생략하고 () 만 사용한 클래스, 일반적으로 사용하는 방식
class User5() {}

//  클래스의 주 생성자에 매개변수가 없을 경우 주 생성자인 () 도 생략 가능함
class User6 {}


//  자바에서 매개변수가 있는 생성자를 사용한 클래스
//class User7 {
//  public User7(String name, int count) {
//
//  }
//}

//  주 생성자에 매개변수를 사용한 클래스
class User7 constructor(name: String, count: Int) {

//  클래스의 필드 선언
  var name: String
  var count: Int
  
//  코틀린의 주 생성자는 매개변수를 입력하는 () 뒤에 생성자의 내용을 입력하는 {} 을 사용할 수 없음
//  클래스 내부에 init {} 이라는 코드블럭을 사용하여 주 생성자의 내용을 입력함
  init {
    //  여기에 주 생성자의 내용을 입력
    println("주 생성자의 내용을 입력하는 init 코드 블럭")
    //  생성자의 매개변수로 전달받은 변수 사용
    println("name : $name, count : $count")
//    this 는 자바의 this 와 동일한 의미
//    생성자의 매개변수로 전달받은 데이터를 사용하여 클래스의 필드를 초기화
    this.name = name
    this.count = count
  }

  fun someMethod() {
    println("클래스 User7의 멤버인 메소드 호출")
//    생성자를 통해서 전달받은 매개변수는 init {} 코드 블럭 안에서 사용함
//    생성자에서 전달받은 매개변수를 메소드에서 사용하려면 필드로 선언해야 함
    println("name : $name, count : $count")
  }
}

//  주 생성자의 매개변수에 var, val 을 사용 시 해당 클래스의 필드로 인식
//  매개변수로 전달받은 데이터를 필드에 자동으로 대입함
class User8 (val name: String, var count: Int) {
//  생성자의 매개변수에 var, val 을 사용하여 매개변수가 클래스의 필드가 되었기 때문에 따로 필드를 입력하지 않아도 됨
//  var name: String
//  var count: Int

//    생성자의 매개변수에 var, val 을 사용하여 매개변수가 필드가 되고 전달받은 데이터가 자동으로 대입되기 때문에 this. 를 사용한 필드의 데이터 초기화가 필요없음
//  init {
//    this.name = name
//    this.count = count
//  }

  fun someMethod() {
    println("클래스의 멤버 메소드")
    println("name : $name, count : $count")
  }
}

//  보조 생성자를 사용하는 클래스
//  보조 생성자만 사용하는 클래스는 자바의 클래스 선언 및 사용 방식과 비슷함
class User9 {

  var name: String = ""
  var count: Int = 0

//    보조 생성자
//  보조 생성자는 constructor 라는 이름을 생략할 수 없음
  constructor() {
    println("보조 생성자 호출")
  }
  
//  보조 생성자는 생성자 오버로딩이 가능함
  constructor(name: String) {
    println("두번째 보조 생성자")
    println("name : $name")
    this.name = name
  }

  constructor(name: String, count: Int) {
    println("세번째 보조 생성자")
    println("name : $name, count : $count")
//    매개변수로 받아온 데이터를 클래스의 필드에 대입하여 필드 초기화
    this.name = name
    this.count = count
  }


  fun someMethod() {
    println("보조 생성자를 사용한 클래스 User9")
    println("name : $name, count : $count")
  }
}

//  주 생성자나 보조 생성자를 반드시 사용할 필요는 없음
//  주 생성자만 사용하거나 보조 생성자만 사용하는 것도 가능함(개발자 취향)
//  주 생성자와 보조 생성자를 함께 사용 시 반드시 보조 생성자에서 주 생성자를 호출해야 함

//  주 생성자의 매개변수에 var, val 을 사용하여 필드로 인식 및 데이터 초기화
class User10(var name: String) {

//  클래스의 필드 선언
  var count: Int = 0
  var email: String = ""

//  주 생성자의 소스 코드 실행 코드 블럭
  init {
    println("매개변수가 1개인 주 생성자 호출")
  }

//  보조 생성자
//  자바의 this() 명령어와 같이 this() 를 사용하여 주 생성자를 호출해야 함
//  보조 생성자 선언부 뒤에 :this() 를 사용하여 주 생성자를 호출
  constructor(name: String, count: Int): this(name) {
    println("매개변수가 2개인 보조 생성자 호출")
    this.count = count
  }

//  오버로딩된 보조 생성자
  constructor(name: String, count: Int, email: String) : this(name, count) {
    println("매개변수가 3개인 보조 생성자 호출")
    this.email = email
  }

  fun fieldPrint() {
    println("클래스의 필드 값 출력\nname : $name, count : $count, email : $email")
  }
}




fun main() {
  println("----- 클래스 사용하기 -----\n")

//  코틀린의 클래스로 객체 생성
//  코틀린에서는 객체 생성 시 new 키워드를 사용하지 않음
//  코틀린에서는 클래스 타입의 변수로 객체 변수를 선언하지 않음 (var, val 키워드 사용)
  val user = User("choi")
//  객체의 멤버 접근 방식은 자바와 동일함
  user.someFun()

//  자바의 경우
//  User user = new User("choi");
//  user.someFun();

//  빈 클래스로 객체 생성
  val user2 = User2() // 빈 클래스이며 {} 도 생략한 클래스로 객체 생성
  val user3 = User1() // 일반적인 빈 클래스로 객체 생성
  val user4 = User4() // 주 생성자의 이름인 constructor() 을 사용한 빈 클래스로 객체 생성
  val user5 = User5() // 주 생성자의 이름을 생략하고 () 만 사용한 빈 클래스로 객체 생성
  val user6 = User6() // 일반적인 빈 클래스로 객체 생성

//  매개변수를 전달하여 객체 생성
  val user7 = User7("아이유", 1)
  user7.someMethod()

  val user8 = User8("유재석", 2)
  user8.someMethod()

  println("\n----- 보조 생성자 -----\n")
  
  val user91 = User9()
  user91.someMethod()

  println()

  val user92 = User9("유인나")
  user92.someMethod()

  println()

  val user93 = User9("유병재", 3)
  user93.someMethod()

  println("\n----- 주 생성자와 보조 생성자 동시 사용 -----\n")

  val user101 = User10("유인나")
  user101.fieldPrint()

  println()

  val user102 = User10("아이유", 102)
  user102.fieldPrint()

  println()

  val user103 = User10("아이유", 103, "iu@gmail.com")
  user103.fieldPrint()

}














