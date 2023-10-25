import UIKit
import SwiftUI
import shared
import Lottie

class IOSLottieView : CommonLottieView {
  func viewAnimation() -> Any? {
    let lottieAnimationView = LottieAnimationView.init(name: "bus")
    lottieAnimationView.play(toProgress: 1, loopMode: .loop)
    return lottieAnimationView
  }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
      MainIOSKt.MainViewController(commonLottieView: IOSLottieView())
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}



