plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
//  패키지 명
  namespace = "bitc.fullstack.myapp"
//  현재 안드로이드 앱 개발에 사용하는 SDK 버전
  compileSdk = 35

  defaultConfig {
    applicationId = "bitc.fullstack.myapp"
//    지원하는 최소 SDK 버전
    minSdk = 29
//    개발하는 SDK 버전
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
//  코틀린이 자바로 변환될 때 사용하는 자바 버전
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  // 추가 옵션 넣기
}

// 종속성 라이브러리
dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}