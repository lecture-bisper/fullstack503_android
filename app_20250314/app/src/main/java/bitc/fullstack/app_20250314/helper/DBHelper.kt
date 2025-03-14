package bitc.fullstack.app_20250314.helper

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

//  SQLiteOpenHelper 안드로이드 기본 라이브러리에서 제공하는 SQLite 를 쉽게 사용하기 위한 추상 클래스
//  SQLiteOpenHelper 상속받아 구현하여 사용
class DBHelper(context: Context) : SQLiteOpenHelper(context, "testdb.sqlite", null, 3) {

//  앱 설치 시 단 한번만 실행되는 메소드
//  주로 데이터 베이스 테이블 생성 명령을 입력함
  override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL("CREATE TABLE phonebook ( \n" +
        "seq       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
        "name      TEXT NOT NULL, \n" +
        "phone     TEXT NOT NULL, \n" +
        "email     TEXT, \n" +
        "address   TEXT) ")

  Log.d("fullstack503", "앱 설치 시 한번만 실행되는 onCreate() 호출")
  }

//  앱 버전 정보가 변경될 때 호출되는 메소드
  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    Log.d("fullstack503", "데이터베이스 변경시 실행되는 onUpgrade() 호출")
  }

  
//  사용자가 생성한 데이터베이스 제어용 메소드들
  fun dbInsert(db: SQLiteDatabase?, member: PhoneBookData) {
    var sql = "INSERT INTO phonebook (name, phone, email, address) " +
        "VALUES ('${member.name}', '${member.phone}', '${if (member.email != null) member.email else null}', '${if (member.address != null) member.address else null}') "

//    execSQL() : insert, update, delete SQL 쿼리문을 실행하는데 사용하는 메소드
//    첫번째 매개변수는 SQL 쿼리
//      첫번째 매개변수인 SQL 쿼리문에 데이터가 모두 포함되어 있으면 두번째 매개변수는 생략가능
//    두번째 매개변수는 SQL 쿼리문에 포함된 ? 기호에 입력할 데이터, 데이터는 배열로 입력
//      쿼리문의 ? 의 순서대로 배열의 데이터가 대입 됨, 0 번 부터 시작
    db?.execSQL(sql)
  }

  fun dbUpdate(db: SQLiteDatabase?, member: PhoneBookData) {
    val sql = "UPDATE phonebook SET " +
        "name = ?, " +
        "phone = ?, " +
        "email = ?, " +
        "address = ? "

    db?.execSQL(sql, arrayOf(member.name, member.phone, member.email, member.address))
  }

  fun dbDelete(db: SQLiteDatabase?, seq: Int) {
    val sql = "DELETE FROM phonebook " +
        "WHERE seq = $seq"

    db?.execSQL(sql)
  }

//  rawQuery() : select 쿼리문을 실행하는 메소드, 조회 결과로 Cursor 타입의 객체를 출력
  fun dbSelectSeq(db: SQLiteDatabase?, seq: Int) : String {
    val sql = "SELECT seq, name, phone, email, address FROM phonebook " +
        "WHERE seq = $seq "
    val data = db?.rawQuery(sql, null)

    var result = ""

//  moveToNext() : 조회 결과로 받은 Cursor 객체에서 다음 데이터가 있는지 확인하는 메소드
//  getString(index) : moveToNext() 로 가져온 데이터 묶음에서 원하는 데이터를 가져오기 위해 사용하는 메소드, index 기반으로 원하는 데이터를 가져옴, index는 select 절의 column 명 순서로 판단하고 0부터 시작, 반환값이 String 타입
//  getInt(index) : getString() 과 동일한 메소드이며 반환값이 Int 타입
    while (data!!.moveToNext()) {
      result += "번호 : ${data.getInt(0)} \n" +
          "이름 : ${data.getString(1)} \n" +
          "휴대폰 : ${data.getString(2)} \n" +
          "이메일 : ${data.getString(3)} \n" +
          "주소 : ${data.getString(4)}"
    }

    return result
  }

  fun dbSelectName(db: SQLiteDatabase?, name: String) : String {
    val sql = "SELECT seq, name, phone, email, address FROM phonebook " +
        "WHERE name like '%$name%' "
    val data = db?.rawQuery(sql, null)

    var result = ""

    while (data!!.moveToNext()) {
      result += "번호 : ${data.getInt(0)} \n" +
          "이름 : ${data.getString(1)} \n" +
          "휴대폰 : ${data.getString(2)} \n" +
          "이메일 : ${data.getString(3)} \n" +
          "주소 : ${data.getString(4)} \n" +
          "\n---------------\n"
    }

    return result
  }

  fun dbSelectAll(db: SQLiteDatabase?): String {
    val sql = "SELECT seq, name, phone, email, address FROM phonebook "
    val data = db?.rawQuery(sql, null)

    var result: String = ""

    while (data!!.moveToNext()) {
      result += "번호 : ${data.getInt(0)} \n" +
          "이름 : ${data.getString(1)} \n" +
          "휴대폰 : ${data.getString(2)} \n" +
          "이메일 : ${data.getString(3)} \n" +
          "주소 : ${data.getString(4)} \n" +
          "\n---------------\n"
    }

    return result
  }


}














