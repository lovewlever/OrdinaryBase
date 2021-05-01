plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 26
        targetSdk = 30
/*        versionCode = 1
        versionName = "1.0"*/

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    api ("com.jakewharton.timber:timber:4.7.1")
    api ("androidx.datastore:datastore-preferences-core:1.0.0-alpha08")
    api ("androidx.datastore:datastore-preferences:1.0.0-alpha08")

    val navVersion = "2.3.5"
    api ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    api ("androidx.navigation:navigation-ui-ktx:$navVersion")
    // Feature module Support
    api ("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
    api ("androidx.fragment:fragment-ktx:1.3.3")
    api ("androidx.activity:activity-ktx:1.2.2")
    api ("androidx.recyclerview:recyclerview:1.1.0")
    // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
    api (group= "com.github.bumptech.glide", name= "glide", version= "4.12.0")
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    api (group = "com.squareup.retrofit2", name = "retrofit", version= "2.9.0")
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    api (group= "com.squareup.retrofit2", name= "converter-gson", version= "2.9.0")
    // https=//mvnrepository.com/artifact/com.squareup.retrofit2/adapter-rxjava3
    api (group= "com.squareup.retrofit2", name= "adapter-rxjava3", version= "2.9.0")
    api ("com.squareup.okhttp3:logging-interceptor:4.1.0")
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    api (group= "com.squareup.okhttp3", name= "okhttp", version= "4.9.1")

    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxjava
    //api (group= "io.reactivex.rxjava3", name= "rxjava", version= "3.0.11")
    // https=//mvnrepository.com/artifact/io.reactivex.rxjava3/rxandroid
    //api (group= "io.reactivex.rxjava3", name= "rxandroid", version= "3.0.0")
    api (group= "com.google.code.gson", name= "gson", version= "2.8.6")
    api ("org.jetbrains.kotlin:kotlin-reflect:1.4.32")

}