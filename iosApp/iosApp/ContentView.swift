import UIKit
import SwiftUI
import shared
import Lottie

class IOSLottieBus : IOSLottieView {
  func viewAnimation(iterations: Int32, progressRange: KotlinPair<KotlinFloat, KotlinFloat>) -> Any? {
    let lottieAnimationView = LottieAnimationView.init(name: "bus")
    var loopMode: LottieLoopMode = .repeat(Float(iterations))
    if (iterations == Int32.max) {
      loopMode = .loop
    }
    lottieAnimationView.play(fromProgress: CGFloat(truncating: progressRange.first ?? 0), toProgress: CGFloat(truncating: progressRange.second ?? 1), loopMode: loopMode)
    return lottieAnimationView
  }
}

class IOSLottieEye : IOSLottieView {
  func viewAnimation(iterations: Int32, progressRange: KotlinPair<KotlinFloat, KotlinFloat>) -> Any? {
    let lottieAnimationView = LottieAnimationView.init(name: "eye")
    var loopMode: LottieLoopMode = .repeat(Float(iterations))
    if (iterations == Int32.max) {
      loopMode = .loop
    }
    lottieAnimationView.play(fromProgress: CGFloat(truncating: progressRange.first ?? 0), toProgress: CGFloat(truncating: progressRange.second ?? 1), loopMode: loopMode)
    return lottieAnimationView
  }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
      MainIOSKt.MainViewController(
        iosLottieView: IOSLottieBus(),
        iosLottieEye: IOSLottieEye(),
        loginScreenViewModel: LoginScreenViewModelImpl(),
        signupScreenViewModel: SignupScreenViewModelImpl()
      )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}



