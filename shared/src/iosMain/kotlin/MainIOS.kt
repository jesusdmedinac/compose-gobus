import androidx.compose.ui.window.ComposeUIViewController
import com.jesusdmedinac.compose.gobus.presentation.ui.ComposeGobusApp
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieViewImpl
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieView
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignupScreenViewModel

fun MainViewController(
    iosLottieView: IOSLottieView,
    iosLottieEye: IOSLottieView,
    loginScreenViewModel: LoginScreenViewModel,
    signupScreenViewModel: SignupScreenViewModel,
) =
    ComposeUIViewController {
        ComposeGobusApp(
            iosLottieView,
            AndroidLottieViewImpl,
            iosLottieEye,
            AndroidLottieViewImpl,
            loginScreenViewModel,
            signupScreenViewModel,
        )
    }
