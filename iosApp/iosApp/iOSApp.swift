import SwiftUI
import shared

@main
struct iOSApp: App {
  init() {
    let firebaseApp = FirebaseInitKt.doInitFirebase(context: nil)
    KoinInitKt.doInitKoin(firebaseApp: firebaseApp)
  }
  
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
