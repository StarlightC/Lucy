import com.spicycold.lucy.version.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.spicycold.lucy.version")
}

android {
    namespace = "com.spicycold.lucy.base"
    compileSdk = CompileVersion.CompileSDK

    defaultConfig {
        minSdk = CompileVersion.MinAndroidSDK

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
        sourceCompatibility = CompileVersion.Java
        targetCompatibility = CompileVersion.Java
    }
    kotlinOptions {
        jvmTarget = CompileVersion.JVM
    }
}

dependencies {
    api("androidx.core:core-ktx:${AndroidVersion.Core}")
    api("androidx.appcompat:appcompat:${AndroidVersion.AppCompat}")
    api("androidx.lifecycle:lifecycle-runtime-ktx:${AndroidVersion.Lifecycle}")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:${AndroidVersion.Lifecycle}")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:${AndroidVersion.Lifecycle}")
    api("androidx.activity:activity-compose:${AndroidVersion.Activity}")
    api(platform("androidx.compose:compose-bom:${AndroidVersion.ComposeBOM}"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.material3:material3")
    testImplementation("junit:junit:${AndroidVersion.JavaUnit}")
    androidTestImplementation("androidx.test.ext:junit:${AndroidVersion.AndroidJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${AndroidVersion.Espresso}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${AndroidVersion.ComposeBOM}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}