package com.surajrathod.bcawave.ui.programdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.theme.SubColor

@Composable
fun CommonTextBadge(
    modifier: Modifier,
    text: String,
    textColor: Color = Color.Black,
    backColor: Color = Color.White
) {
    Box(modifier = modifier.background(backColor, CircleShape.copy(CornerSize(4.dp)))) {
        Text(
            text = text, color = textColor, fontFamily = Font(
                R.font.main_bold
            ).toFontFamily(),
            modifier = Modifier.padding(horizontal = 4.dp), fontSize = 14.sp
        )
    }
}


@Preview
@Composable
fun CommonTextPreview() {
    CommonTextBadge(
        modifier = Modifier,
        text = "Suraj",
        textColor = SubColor,
        backColor = Color.White
    )
}