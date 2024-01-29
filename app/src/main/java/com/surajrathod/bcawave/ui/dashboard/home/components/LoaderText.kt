package com.surajrathod.bcawave.ui.dashboard.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

@Composable
fun LoaderText(modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("Loading")
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(150)
            text = "Loading."
            delay(150)
            text = "Loading.."
            delay(150)
            text = "Loading..."
        }
    }
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(color = PrimaryColor),
        fontFamily = Font(
            R.font.main_bold
        ).toFontFamily()
    )
}