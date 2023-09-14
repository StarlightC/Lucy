package com.spicycold.lucy.version

import org.gradle.api.JavaVersion

/**
 * @author StarlightC
 * @since 14/9/2023
 *
 * Compile Version
 */
object CompileVersion {
    const val Code = 1
    const val Name = "0.0"

    @JvmStatic
    val Java = JavaVersion.VERSION_17
    const val JVM = "17"

    const val CompileSDK = 34
    const val MinAndroidSDK = 27
    const val TargetSDK = 34
    const val CMake = "3.22.1"
    const val KotlinCompilerExtension = "1.4.3"
}