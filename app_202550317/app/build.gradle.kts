plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "bitc.fullstack.app_202550317"
  compileSdk = 35

  defaultConfig {
    applicationId = "bitc.fullstack.app_202550317"
    minSdk = 29
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
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  viewBinding {
    enable = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

//  Volley 라이브러리 사용
  // https://mvnrepository.com/artifact/com.android.volley/volley
  implementation(libs.volley)

//  Gson 라이브러리 사용
  // https://mvnrepository.com/artifact/com.google.code.gson/gson
  implementation(libs.gson)

//  Retrofit2 라이브러리 사용
  // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
  implementation(libs.retrofit)

//  Retrofit2 용 xml 파싱 라이브러리 사용
  // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-simplexml
  implementation(libs.converter.simplexml)

//  Retrofit2 용 json 파싱 라이브러리 사용
  // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
  implementation(libs.converter.gson)

//  Glide 라이브러리 사용
  // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
  implementation(libs.glide)

}