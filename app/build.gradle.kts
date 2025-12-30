//plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.jetbrainsKotlinAndroid)
//    id("com.google.gms.google-services")
//}
//
//android {
//    namespace = "com.hussain.myapplication"
//    compileSdk = 34
//
//    defaultConfig {
//        applicationId = "com.hussain.myapplication"
//        minSdk = 23
//        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//
//        multiDexEnabled = true
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
////    packagingOptions{
////        exclude("META-INF/DEPENDENCIES")
////    }
//    packagingOptions {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//           // exclude 'META-INF/gradle/incremental.annotation.processors'
//        }
//    }
//    // Add packaging block to exclude conflicting META-INF files
////    packaging {
////        resources {
////            excludes += ['META-INF/INDEX.LIST']
////        }
////    }
//}
//
//dependencies {
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.firebase.messaging)
//    implementation(libs.firebase.messaging.ktx)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//
//    implementation("com.squareup.retrofit2:retrofit:2.11.0")
//    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
//    implementation("com.karumi:dexter:6.2.3")
////    implementation("com.google.auth:google-auth-library-oauth2-http:1.38.0")
////    implementation ("com.google.auth:google-auth-library-oauth2-http:1.17.0")
//
//    implementation("com.google.auth:google-auth-library-oauth2-http:1.24.0")
//// Use the latest valid version
//    implementation("com.google.auth:google-auth-library-credentials:1.24.0")
//// Use the latest valid version
//
//    //________________________________________________
//
//  /*  implementation platform('com.google.firebase:firebase-bom:32.0.0') // Use the latest version
//    implementation 'com.google.firebase:firebase-messaging'*/
//    implementation ("com.google.firebase:firebase-bom:32.0.0")
//    implementation ("com.google.firebase:firebase-messaging")
//    implementation("com.google.firebase:firebase-installations:18.0.0")
//
//
//
//
//
//
//
//}



plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.hussain.myapplication"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.hussain.myapplication"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/license.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/INDEX.LIST"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/notice.txt"
            excludes += "/META-INF/ASL2.0"
            excludes += "/META-INF/*.kotlin_module"
        }
    }


}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.messaging)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") // Align with Retrofit version
    implementation("com.karumi:dexter:6.2.3")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.24.0")
    implementation("com.google.auth:google-auth-library-credentials:1.24.0")
    implementation("com.google.firebase:firebase-bom:33.5.1") // Use the latest BOM version
    implementation("com.google.firebase:firebase-messaging")

    implementation("androidx.core:core:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0") // Updated and aligned

    // Ensure this is included
    implementation("com.google.firebase:firebase-installations")
   // implementation("org.webrtc:webrtc-android:120.0.0")
    implementation("io.agora.rtc:full-sdk:4.2.0")
// Use latest version
}