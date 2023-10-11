package com.spicycold.lucy.base.tool

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope


/**
 * @since 2022/4/24
 *
 * 系统方法调用工具
 */
object AndroidSystemTool {
    fun setRequestedOrientation(context: Context?, orientation: Int) {
        if (getAppCompatActivity(context) != null) {
            getAppCompatActivity(context)?.requestedOrientation = orientation
        } else {
            getActivityViaContext(context)?.requestedOrientation = orientation
        }
    }

    fun getRequestedOrientation(context: Context?): Int {
        return if (getAppCompatActivity(context) != null) {
            getAppCompatActivity(context)?.requestedOrientation ?: 0
        } else {
            getActivityViaContext(context)?.requestedOrientation ?: 0
        }
    }

    fun getAppCompatActivity(context: Context?): AppCompatActivity? {
        return when(context) {
            null -> null
            is AppCompatActivity -> context
            is ContextThemeWrapper -> getAppCompatActivity(context.baseContext)
            else -> null
        }
    }

    fun getActivityViaContext(context: Context?): Activity? {
        return when(context) {
            null -> null
            is Activity -> context
            is ContextWrapper -> getActivityViaContext(context.baseContext)
            else -> null
        }
    }

    fun getLifecycleScopeViaContext(context: Context?): LifecycleCoroutineScope? {
        return when(context) {
            null -> null
            is AppCompatActivity -> context.lifecycleScope
            is LifecycleOwner -> context.lifecycleScope
            is ContextWrapper -> getLifecycleScopeViaContext(context.baseContext)
            else -> null
        }
    }
}