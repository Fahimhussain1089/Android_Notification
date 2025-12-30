//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

buildscript {
    dependencies {
        classpath(libs.google.services)
       // classpath ("com.android.tools.build:gradle:8.1.1")
//        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("com.google.gms:google-services:4.4.3")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}