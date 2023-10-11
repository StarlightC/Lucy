package com.spicycold.lucy.base.tool

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat


/**
 * @since 2022/4/24
 *
 * 显示相关工具
 */
object DisplayTool {

    fun getViewWidth(view: View): Int {
        return if (view.width > 0) {
            view.width
        } else if (view.layoutParams != null && view.layoutParams.width > 0) {
            view.layoutParams.width
        } else {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            view.measuredWidth
        }
    }

    fun getViewHeight(view: View): Int {
        return if (view.height > 0) {
            view.height
        } else if (view.layoutParams != null && view.layoutParams.height > 0) {
            view.layoutParams.height
        } else {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            view.measuredHeight
        }
    }

    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    private fun getWindow(context: Context?): Window? {
        return if (AndroidSystemTool.getAppCompatActivity(context) != null) {
            AndroidSystemTool.getAppCompatActivity(context)?.window
        } else {
            AndroidSystemTool.getActivityViaContext(context)?.window
        }
    }

    /**
     * 修改状态栏字体颜色
     */
    fun lightStatusBar(activity: Activity, isLight : Boolean) {
        val windowInsetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = isLight
    }

    /**
     * 设置状态栏背景色
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        activity.window.statusBarColor = color
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(view: View): Int {
        val insets = ViewCompat.getRootWindowInsets(view)
            ?.getInsets(WindowInsetsCompat.Type.statusBars())
        return insets?.bottom ?: getPreDefinedStatusBarHeight(view.context)
    }

    fun getPreDefinedStatusBarHeight(context: Context): Int {
        val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 获取导航栏高度
     * @param 需要View已经Attach
     */
    fun getNavigationBarHeight(view: View): Int {
        val insets = ViewCompat.getRootWindowInsets(view)
            ?.getInsets(WindowInsetsCompat.Type.navigationBars())
        //WindowInsets为null则默认通过资源获取高度
        return insets?.bottom ?: getPreDefinedNavigationBarHeight(view.context)
    }

    /**
     * 获取预定义的导航栏高度
     */
    fun getPreDefinedNavigationBarHeight(context: Context): Int {
        val resourceId: Int = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    fun showSystemUI(activity: Activity) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        WindowCompat.getInsetsController(activity.window, activity.window.decorView).show(WindowInsetsCompat.Type.systemBars())
    }

    fun hideSystemUI(activity: Activity) {
        activity.window.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            WindowCompat.getInsetsController(it, it.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    fun showStatusBar(activity: Activity) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        WindowCompat.getInsetsController(activity.window, activity.window.decorView).show(WindowInsetsCompat.Type.statusBars())
    }

    fun hideStatusBar(activity: Activity) {
        activity.window.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            WindowCompat.getInsetsController(it, it.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.statusBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    fun showNavigationBar(activity: Activity) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        WindowCompat.getInsetsController(activity.window, activity.window.decorView).show(WindowInsetsCompat.Type.navigationBars())
    }

    fun hideNavigationBar(activity: Activity) {
        activity.window.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            WindowCompat.getInsetsController(it, it.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

}