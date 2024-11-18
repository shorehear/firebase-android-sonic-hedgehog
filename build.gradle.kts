buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}

plugins {
    id("com.android.application") version "8.6.0" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}
