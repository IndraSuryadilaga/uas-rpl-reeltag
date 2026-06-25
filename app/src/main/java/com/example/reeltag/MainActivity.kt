package com.example.reeltag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reeltag.navigation.AppNavigation
import com.example.reeltag.ui.theme.ReelTagTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            ReelTagTheme {

                AppNavigation()

            }

        }

    }

}