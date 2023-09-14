import com.spicycold.lucy.version.AndroidVersion
import com.spicycold.lucy.version.CompileVersion

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

    implementation("androidx.core:core-ktx:${AndroidVersion.Core}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${AndroidVersion.Lifecycle}")
    implementation("androidx.activity:activity-compose:${AndroidVersion.Activity}")
    implementation(platform("androidx.compose:compose-bom:${AndroidVersion.ComposeBOM}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:${AndroidVersion.JavaUnit}")
    androidTestImplementation("androidx.test.ext:junit:${AndroidVersion.AndroidJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${AndroidVersion.Espresso}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${AndroidVersion.ComposeBOM}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}