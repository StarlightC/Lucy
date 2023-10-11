package com.spicycold.lucy.floating.service

import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

/**
 * @author StarlightC
 * @since 10/10/2023
 *
 */
abstract class ComposeOverlayService: OverlayService() {

    private val layoutParams by lazy {
        WindowManager.LayoutParams().apply {
            format = PixelFormat.TRANSLUCENT
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            gravity = Gravity.TOP or Gravity.START
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            height = WindowManager.LayoutParams.WRAP_CONTENT
            width = WindowManager.LayoutParams.WRAP_CONTENT
        }
    }

    private val windowManager by lazy {
        overlayContext.getSystemService(WindowManager::class.java)
    }

    private val composeView by lazy {
        ComposeView(overlayContext)
    }

    override fun onCreate() {
        super.onCreate()
        composeView.setViewTreeLifecycleOwner(this)
        composeView.setViewTreeViewModelStoreOwner(this)
        composeView.setViewTreeSavedStateRegistryOwner(this)
        composeView.setContent {
            overlayContent()
        }
        windowManager.addView(composeView, layoutParams)
    }

    override fun onDestroy() {
        super.onDestroy()

        windowManager.removeView(composeView)
    }

    @Composable
    abstract fun overlayContent()
}