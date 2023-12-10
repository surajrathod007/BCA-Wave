package com.surajrathod.bcawave.ui.dashboard.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor

@Composable
fun HomeScreen() {
    Surface(color = PrimaryBgColor, modifier = Modifier.fillMaxSize()) {
        Box {
            Text(
                text = "Home",
                fontFamily = Font(R.font.main_bold).toFontFamily(),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),

            )
        }

    }
}