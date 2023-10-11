package com.spicycold.lucy.floating.service

import android.content.Context
import android.hardware.display.DisplayManager
import android.view.Display
import android.view.WindowManager
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner

/**
 * @author StarlightC
 * @since 15/9/2023
 *
 */
abstract class OverlayService: LifecycleService(), SavedStateRegistryOwner, ViewModelStoreOwner {
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

    override val viewModelStore: ViewModelStore
        get() = internalViewModelStore

    private val savedStateRegistryController: SavedStateRegistryController by lazy {
        SavedStateRegistryController.create(this)
    }

    private val internalViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    /**
     * Context dedicated to the View
     */
    internal val overlayContext: Context by lazy {
        val defaultDisplay: Display = getSystemService(DisplayManager::class.java).getDisplay(Display.DEFAULT_DISPLAY)
        createDisplayContext(defaultDisplay).createWindowContext(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, null)
    }

    override fun onCreate() {
        super.onCreate()
        // Restore the last saved state registry
        savedStateRegistryController.performRestore(null)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}