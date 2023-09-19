import com.spicycold.lucy.version.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.spicycold.lucy.version")
}

android {
    namespace = "com.spicycold.lucy"
    compileSdk = CompileVersion.CompileSDK

    defaultConfig {
        applicationId = "com.spicycold.lucy"
        minSdk = CompileVersion.MinAndroidSDK
        targetSdk = CompileVersion.TargetSDK
        versionCode = CompileVersion.Code
        versionName = CompileVersion.Name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = CompileVersion.KotlinCompilerExtension
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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

    implementation("io.coil-kt:coil-compose:${ThirdPartyVersion.Coil}")
}