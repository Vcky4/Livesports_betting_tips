@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.lsbt.livesportsbettingtips"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.lsbt.livesportsbettingtips"
        minSdk = 21
        targetSdk = 33
        versionCode = 8
        versionName = "1.1.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        resConfigs("en", "es", "fr", "pt")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

//    androidResources{
//        generateLocaleConfig = true
//    }
    // Instead, use the bundle block to control which types of configuration APKs
// you want your app bundle to support.
    bundle {
        language {
            // Specifies that the app bundle should not support
            // configuration APKs for language resources. These
            // resources are instead packaged with each base and
            // dynamic feature APK.
            enableSplit = false
        }
        density {
            // This property is set to true by default.
            enableSplit = true
        }
        abi {
            // This property is set to true by default.
            enableSplit = true
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.inappmessaging.display.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.volley)
    implementation(libs.firebase.storage.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    //destinations
    implementation(libs.destinations.core)
    ksp(libs.destinations.ksp)

    // Koin
    implementation(libs.koin)
    implementation(libs.koin.worker)

    // livedata
    implementation(libs.livedata)
    implementation(libs.livedata.runtime)

    // room
    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.datastore.preferences)
    ksp(libs.room.compiler)

    //pdf viewer
    implementation(libs.bouquet)

    //coil
    implementation(libs.coil.compose)
}