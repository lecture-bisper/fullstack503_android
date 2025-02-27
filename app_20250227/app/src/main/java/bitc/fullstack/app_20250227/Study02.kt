package bitc.fullstack.app_20250227

fun main() {
//  배열 : 코틀린의 배열을 클래스로 표현
//  기본 사용법(데이터의 입력, 출력)은 자바의 배열과 동일함

//  사용법(선언)
//  val 배열명: 데이터타입 = Array(배열 크기, {초기값})

  println("----- 배열 사용하기 -----\n")

  val arrData1: Array<Int> = Array(5, { 0 })
  println(arrData1)
  println("배열 arrData1의 0번 index 값 : ${arrData1[0]}")
  println("배열 arrdata1의 4번 index 값 : ${arrData1[4]}")

  println("---- 데이터 수정 후 배열 값 확인 -----")
//  생성된 배열에 접근 시 [index] 를 사용하여 접근
  arrData1[0] = 10
  arrData1[1] = 20
  arrData1[2] = 30
//  생성된 배열의 get()/set() 메소드를 사용하여 접근
//  set(index, 저장할 데이터) 형태로 데이터 입력
//  get(index) 형태로 데이터 출력
  arrData1.set(3, 40)
  arrData1.set(4, 50)

  println(
    """
    array size : ${arrData1.size}
    array data : ${arrData1[0]}, ${arrData1[1]}, ${arrData1.get(2)}, ${arrData1.get(3)}, ${
      arrData1.get(
        4
      )
    }
  """.trimIndent()
  )

//  코틀린에서는 기본 타입 배열 클래스를 제공하고 있음
//  ByteArray, ShortArray, IntArray, LongArray, FloatArray, DoubleArray, BooleanArray, CharArray 클래스를 제공함
//  기본 타입 배열 클래스를 사용 시 타입이 지정되어 있기 때문에 제네릭을 통한 데이터 타입 지정을 안함

  println("\n ----- 기본 타입 배열 클래스로 배열 만들기 -----\n")
  val arrByte: ByteArray = ByteArray(3, { 0 })
  val arrShort: ShortArray = ShortArray(3, { 0 })
  val arrInt = IntArray(3, { 0 })
  val arrLong = LongArray(3, { 0 })
  val arrFloat: FloatArray = FloatArray(3, { 0.0F })
  val arrDouble = DoubleArray(3, { 0.0 })
  val arrChar: CharArray = CharArray(3, { 'a' })
  val arrBoolean = BooleanArray(3, { false })

  println("byte 배열 - 크기 : ${arrByte.size}, 값 : ${arrByte[0]}")
  println("short 배열 - 크기 : ${arrShort.size}, 값 : ${arrShort[0]}")
  println("int 배열 - 크기 : ${arrInt.size}, 값 : ${arrInt.get(0)}")
  println("long 배열 - 크기 : ${arrLong.size}, 값 : ${arrLong.get(0)}")
  println("double 배열 - 크기 : ${arrDouble.size}, 값 : ${arrDouble.get(0)}")
  println("float 배열 - 크기 : ${arrFloat.size}, 값 : ${arrFloat[0]}")
  println("double 배열 - 크기 : ${arrDouble.size}, 값 : ${arrDouble.get(0)}")
  println("char 배열 - 크기 : ${arrChar.size}, 값 : ${arrChar[0]}")
  println("boolean 배열 - 크기 : ${arrBoolean.size}, 값 : ${arrBoolean.get(0)}")


//  코틀린에서 배열 선언과 동시에 데이터 입력
//  arrayOf() : 코틀린에서 배열 생성과 동시에 지정한 값으로 데이터를 초기화하는 함수
//  기본 타입 배열 클래스용 arrayOf() 가 존재함
//  byteArrayOf(), shortArrayOf(), intArrayOf(), longArrayOf(), floatArrayOf(), doubleArrayOf(), charArrayOf(), booleanArrayOf()

//  사용법
//  val 배열명: Array<데이터타입> = arrayOf(데이터1, 데이터2, 데이터3, ... )

  println("\n----- arrayOf() 를 사용하여 배열 생성 -----\n")

  val arrData2: Array<Int> = arrayOf(10, 20, 30)
  println(arrData2)
  println("arrayOf()로 만든 배열 0번 index : ${arrData2[0]}")
  println("arrayOf()로 만든 배열 1번 index : ${arrData2[1]}")
  println("arrayOf()로 만든 배열 2번 index : ${arrData2.get(2)}")

  println("----- 기본 타입 arrayOf() 로 배열 생성 -----")
  val arrByte2: ByteArray = byteArrayOf(0b00, 0b01, 0b10)
  val arrShort2: ShortArray = shortArrayOf(10, 20, 30)
  val arrInt2 = intArrayOf(10, 20, 30)
  val arrLong2 = longArrayOf(10L, 20L, 30L)
  val arrFloat2: FloatArray = floatArrayOf(0.1F, 0.2F, 0.3F)
  val arrDouble2 = doubleArrayOf(0.1, 0.2, 0.3)
  val arrChar2: CharArray = charArrayOf('a', 'b', 'c')
  val arrBoolean2 = booleanArrayOf(true, false)

  println("arrByte2의 0 index : ${arrByte2[0]}")
  println("arrShort2의 0 index : ${arrShort2[0]}")
  println("arrInt2의 0 index : ${arrInt2[0]}")
  println("arrLong2의 0 index : ${arrLong2[0]}")
  println("arrFloat2의 0 index : ${arrFloat2[0]}")
  println("arrDouble2의 0 index : ${arrDouble2[0]}")
  println("arrChar2의 0 index : ${arrChar2[0]}")
  println("arrBoolean2의 0 index : ${arrBoolean2[0]}")


  println("\n ----- List, Set, Map -----\n")
//  코틀린의 List, Set, Map는 배열과 같이 클래스임
//  listOf(), setOf(), mapOf() 를 주로 사용하여 List, Set, Map을 생성함
//  코틀린의 컬렉션 타입은 Mutable, Immutable 라는 개념이 추가 됨
//  Mutable 은 수정 가능한 타입, 처음 데이터 초기화 후 데이터 수정 가능
//  Immutable 은 수정 불가능한 타입, 처음 데이터 초기화 후 데이터 수정 불가
//  Immutable 방식이 코틀린 컬렉션 타입의 기본임
//  Mutable 방식의 List, Set, Map을 생성하려면 mutableListOf(), mutableSetOf(), mutableMapOf() 를 사용하여 생성해야 함

//  아래의 리스트는 기본 리스트 생성 방식을 사용했기 때문에 Immutable(불변 클래스) 방식 List임
  val list1: List<Int> = List(3, { 0 }) // 기본 방식
  val list2 = listOf(10, 20, 30) // 주로 사용하는 방식
  val list3: List<Int> = listOf(10, 20, 30)
  val list4 = listOf<Int>(10, 20, 30)

  println(
    """
    list1 size : ${list1.size}
    list1 data : ${list1[0]}, ${list1[1]}, ${list1.get(2)}
  """.trimIndent()
  )

  println(
    """
    list2 size : ${list2.size}
    list2 data : ${list2[0]}, ${list2[1]}, ${list2.get(2)}
  """.trimIndent()
  )

  println(
    """
    list3 size : ${list3.size}
    list3 data : ${list3[0]}, ${list3[1]}, ${list3.get(2)}
  """.trimIndent()
  )

  println(
    """
    list4 size : ${list4.size}
    list4 data : ${list4[0]}, ${list4[1]}, ${list4.get(2)}
  """.trimIndent())


//  생성된 리스트가 불변 클래스 타입의 리스트이기 때문에 생성과 동시에 입력된 데이터를 읽기 전용으로 사용함
//  한번 생성된 Immutable 리스트는 데이터의 수정이 불가능 함
//  list1[0] = 100
//  list1.set(1, 200)
//  list2[0] = 100
//  list2.set(1, 200)


  println("----- Mutable 리스트 생성 -----")

  val list5: MutableList<Int> = MutableList(3, {0})
  val list6 = mutableListOf(10, 20, 30)

  println("""
    list5 size : ${list5.size}
    list5 data : ${list5[0]}, ${list5[1]}, ${list5.get(2)}
  """.trimIndent())

  println("""
    list6 size : ${list6.size}
    list6 data : ${list6[0]}, ${list6[1]}, ${list6.get(2)}
  """.trimIndent())

  println("----- Mutable 리스트의 내용 수정 -----")
  list5[0] = 100
  list5.set(1, 200)
  list6[0] = 100
  list6.set(1, 200)

  println("""
    list5 size : ${list5.size}
    list5 data : ${list5[0]}, ${list5[1]}, ${list5.get(2)}
  """.trimIndent())

  println("""
    list6 size : ${list6.size}
    list6 data : ${list6[0]}, ${list6[1]}, ${list6.get(2)}
  """.trimIndent())

//  List 컬렉션 멤버
//  add(index, 데이터) : 지정한 리스트의 원하는 index에 데이터를 추가, 매개변수를 1개만 사용 시 데이터를 기존 리스트의 마지막에 추가함
//  removeAt(index) : 리스트의 지정한 index의 값을 삭제
//  contains() : 지정한 데이터가 리스트에 존재하는지 확인
//  reverse() : 리스트의 순서를 반대로 뒤집어 줌
//  shuffle() : 리스트의 순서를 섞어 줌

  val list7 = mutableListOf(10, 20, 30, 40)
  println(list7.size)
  list7.add(50)
  println(list7.size)
  println(list7[4])
  println(list7[2])
  list7.add(2, 60)
  println(list7[2])
  list7.removeAt(3)
  println(list7.size)
  println(list7[3])


  println("\n\n")

//  Immutable 방식의 Map 객체 생성
//  key의 타입을 String 으로 설정, value의 타입을 String 으로 설정
  val map1 = mapOf<String, String>(Pair("one", "hello"), "two" to "world")
//  Mutablee 방식의 Map 객체 생성
//  key의 타입을 String 으로, value의 타입을 String 으로 자동 추론함
  val map2 = mutableMapOf(Pair("one", "hello"), "two" to "world")

//  코틀린에서 Map 타입에 데이터 입력 시 Pair() 을 사용거나 'to' 키워드를 사용함
//  Pair(key, value) 형태로 사용
//  key to value 형태로 사용

  println("""
    map1 size : ${map1.size}
    map1 data : ${map1.get("one")}, ${map1.get("two")}
  """.trimIndent())

  println("""
    map2 size : ${map2.size}
    map2 data : ${map2.get("one")}, ${map2.get("two")}
  """.trimIndent())

//  Immutable 방식으로 생성된 Map 객체이기 때문에 값을 수정할 수 없음
//  map1["one"] = "헬로"
//  Mutable 방식을 생성된 Map 객체이기 때문에 값을 수정할 수 있음
  map2["two"] = "월드"

//  Map 컬렉션 멤버
//  get(key) : 지정한 key 에 대응하는 value 를 출력
//  set(key, value) : 지정한 key에 대응하는 value 를 Map에 저장
//    지정한 key가 없을 경우 지정한 key와 value를 Map에 추가함
//  size : Map 에 저장된 데이터의 수를 출력, 프로퍼티
//  count() : Map 에 저장된 데이터의 수를 출력, 메소드
//  keys : Map 에 저장된 key 만 모두 모아서 출력
//  values : Map 에 저장된 value 만 모두 모아서 출력
//  containsKey(키명) : 지정한 key가 Map에 저장되어 있는지 확인, true/false
//  containsValue(값) : 지정한 value가 Map에 저장되어 있는지 확인, true/false
//  remove(키명) : 지정한 key와 일치하는 value를 Map에서 삭제
//  clear() : Map 에 저장된 모든 데이터를 삭제

  println("----- map 데이터 추가 -----")
//  []를 사용하는 방식이나 set() 을 사용하는 방식으로 새로운 데이터 추가
//  map2["three"] = "!!!"
  map2.set("three", "!!!")

  println("""
    map2 size : ${map2.count()}
    map2 data : ${map2["one"]}, ${map2["two"]}, ${map2["three"]}
  """.trimIndent())

  println("----- keys/values 사용 -----")
  val keys = map2.keys
  val values = map2.values

  println(keys)
  println(values)

  println(map2.containsKey("one"))
  println(map2.containsKey("aaa"))
  println(map2.containsValue("hello"))
  println(map2.containsValue("$$$"))

  println("map2의 현재 크기 : ${map2.size}")
  map2.remove("three")
  println("map2의 현재 크기 : ${map2.size}")
  map2.clear()
  println("map2의 현재 크기 : ${map2.size}")


}













