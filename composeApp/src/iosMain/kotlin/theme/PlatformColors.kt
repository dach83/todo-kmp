package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.zeroValue
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIView
import platform.UIKit.UIWindow
import platform.UIKit.statusBarManager

@Composable
actual fun PlatformColors(
    statusBarColor: Color,
    navBarColor: Color
) {
    val statusBar = statusBarView()
    SideEffect {
        statusBar.backgroundColor = statusBarColor.toUIColor()

//        UINavigationBar.appearance().backgroundColor = Color.Green.toUIColor()
//        UINavigationBar.appearance().barTintColor = Color.Green.toUIColor()
//        UINavigationBar.appearance().tintColor = Color.Green.toUIColor()
//        UINavigationBar.appearance().translucent = false
//
//        UIBarButtonItem.appearance().tintColor = Color.Magenta.toUIColor()
//        UITabBar.appearance().barTintColor = Color.Yellow.toUIColor()
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
private fun statusBarView() = remember {
    val keyWindow: UIWindow? =
        UIApplication.sharedApplication.windows.firstOrNull {
            (it as? UIWindow)?.isKeyWindow() == true
        } as? UIWindow

    // https://stackoverflow.com/questions/56651245/how-to-change-the-status-bar-background-color-and-text-color-on-ios-13
    val tag = 3848245L
    keyWindow?.viewWithTag(tag) ?: run {
        val height = keyWindow?.windowScene?.statusBarManager?.statusBarFrame ?: zeroValue()
        val statusBarView = UIView(frame = height)
        statusBarView.tag = tag
        statusBarView.layer.zPosition = 999999.0
        keyWindow?.addSubview(statusBarView)
        statusBarView
    }
}

private fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble()
)
