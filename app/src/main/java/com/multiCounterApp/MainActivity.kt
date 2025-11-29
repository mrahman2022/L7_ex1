package com.multiCounterApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.multiCounterApp.ui.CounterListScreen
import com.multiCounterApp.ui.theme.MultiCounterTheme
import com.multiCounterApp.viewmodel.CountersViewModel

class MainActivity : ComponentActivity() {
    private val vm: CountersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiCounterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterListScreen(viewModel = vm)
                }
            }
        }
    }
}
