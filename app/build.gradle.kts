plugins {
    id("com.android.application")
}

android {
    namespace = "com.mrsobakin.piplock"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mrsobakin.piplock"
        minSdk = 29
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }

    signingConfigs {
        val password = System.getenv("SIGNING_PASSWORD")
            ?: error("Signing password should be specified via SIGNING_PASSWORD env")

        create("release") {
            storeFile = file("../signing-key.jks")
            storePassword = password
            keyAlias = "piplock"
            keyPassword = password
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
}
