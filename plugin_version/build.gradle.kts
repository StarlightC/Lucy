plugins {
    id("java-gradle-plugin")
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
}

gradlePlugin {
    plugins {
        create("version") {
            id = "com.spicycold.lucy.version"
            implementationClass = "com.spicycold.lucy.version.VersionPlugin"
        }
    }
}