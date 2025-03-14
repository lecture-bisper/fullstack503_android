package bitc.fullstack.app_20250313

import android.os.Binder

class MyBinder(var msg: String) : Binder() {

  fun sendData(msg: String) {
    this.msg = msg
  }
}














