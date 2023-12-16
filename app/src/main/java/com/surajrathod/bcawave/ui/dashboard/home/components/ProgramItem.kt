package com.surajrathod.bcawave.ui.dashboard.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.Program
import com.surajrathod.bcawave.ui.theme.PrimaryColor


@Preview
@Composable
fun ProgramItem(
    modifier: Modifier = Modifier,
    program: Program = Program(title = "How could I force it to be a single line and draw ellipsis at the end of it? The result I want in this case is something like"),
    onClick: (Program) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape.copy(CornerSize(6.dp)))
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = program.title,
                fontFamily = Font(
                    R.font.main_bold
                ).toFontFamily(),
                color = PrimaryColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}



