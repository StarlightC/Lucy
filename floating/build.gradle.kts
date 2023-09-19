import com.spicycold.lucy.version.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.spicycold.lucy.version")
}

android {
    namespace = "com.spicycold.lucy.floating"
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
    testImplementation("junit:junit:${AndroidVersion.JavaUnit}")
    androidTestImplementation("androidx.test.ext:junit:${AndroidVersion.AndroidJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${AndroidVersion.Espresso}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${AndroidVersion.ComposeBOM}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(project(":base"))

    implementation("androidx.lifecycle:lifecycle-service:${AndroidVersion.Lifecycle}")
    implementation("io.coil-kt:coil-compose:${ThirdPartyVersion.Coil}")
}