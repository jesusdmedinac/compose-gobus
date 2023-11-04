import androidx.compose.ui.window.ComposeUIViewController
import com.jesusdmedinac.compose.gobus.di.GobusKoinHelper
import com.jesusdmedinac.compose.gobus.presentation.ui.ComposeGobusApp
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieViewImpl
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieView
import platform.UIKit.UIViewController

fun MainViewController(
    iosLottieView: IOSLottieView,
    iosLottieEye: IOSLottieView,
): UIViewController {
    val loginScreenViewModel = GobusKoinHelper.loginScreenViewModel()
    val signUpScreenViewModel = GobusKoinHelper.signUpScreenViewModel()
    return ComposeUIViewController {
        ComposeGobusApp(
            iosLottieView,
            AndroidLottieViewImpl,
            iosLottieEye,
            AndroidLottieViewImpl,
            loginScreenViewModel,
            signUpScreenViewModel,
        )
    }
}
