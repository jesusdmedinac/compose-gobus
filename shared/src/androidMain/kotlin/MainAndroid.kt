import androidx.compose.runtime.Composable
import com.jesusdmedinac.compose.gobus.presentation.ui.ComposeGobusApp
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieViewImpl
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignupScreenViewModel

@Composable
fun MainView(
    androidLottieView: AndroidLottieView,
    androidLottieEye: AndroidLottieView,
    loginScreenViewModel: LoginScreenViewModel,
    signupScreenViewModel: SignupScreenViewModel,
) = ComposeGobusApp(
    IOSLottieViewImpl,
    androidLottieView,
    IOSLottieViewImpl,
    androidLottieEye,
    loginScreenViewModel,
    signupScreenViewModel,
)
