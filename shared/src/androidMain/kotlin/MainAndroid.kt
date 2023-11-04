import androidx.compose.runtime.Composable
import com.jesusdmedinac.compose.gobus.presentation.ui.ComposeGobusApp
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieViewImpl
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenViewModel

@Composable
fun MainView(
    androidLottieView: AndroidLottieView,
    androidLottieEye: AndroidLottieView,
    loginScreenViewModel: LoginScreenViewModel,
    signUpScreenViewModel: SignUpScreenViewModel,
) {
    ComposeGobusApp(
        IOSLottieViewImpl,
        androidLottieView,
        IOSLottieViewImpl,
        androidLottieEye,
        loginScreenViewModel,
        signUpScreenViewModel,
    )
}
