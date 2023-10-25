import androidx.compose.ui.window.ComposeUIViewController
import com.jesusdmedinac.compose.gobus.ui.ComposeGobusApp
import com.jesusdmedinac.compose.gobus.ui.composable.CommonLottieView

fun MainViewController(commonLottieView: CommonLottieView) =
    ComposeUIViewController { ComposeGobusApp(commonLottieView) }
