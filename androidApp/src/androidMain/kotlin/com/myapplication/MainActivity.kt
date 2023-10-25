package com.myapplication

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.jesusdmedinac.compose.gobus.ui.composable.CommonLottieView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView(
                commonLottieView = object : CommonLottieView {
                    override fun <T> viewAnimation(): T {
                        return Unit as T // TODO("Not yet implemented")
                    }
                },
            )
        }
    }
}
