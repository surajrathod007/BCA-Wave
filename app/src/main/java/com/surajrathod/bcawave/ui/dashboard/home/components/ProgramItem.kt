package com.surajrathod.bcawave.ui.dashboard.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.Program
import com.surajrathod.bcawave.ui.dashboard.home.ProgramItemData
import com.surajrathod.bcawave.ui.theme.PrimaryColor
import com.surajrathod.bcawave.ui.theme.UnitColor
import com.surajrathod.bcawave.ui.utils.components.HeartButton


@Preview
@Composable
fun ProgramItem(
    modifier: Modifier = Modifier,
    isFav: Boolean = false,
    program: ProgramItemData = ProgramItemData(title = "How could I force it to be a single line and draw ellipsis at the end of it? The result I want in this case is something like"),
    index: String = "0",
    onFavClick: (Boolean, ProgramItemData) -> Unit = { _, _ -> },
    onClick: (ProgramItemData) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(CircleShape.copy(CornerSize(4.dp)))
            .clickable {
                onClick.invoke(program)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(UnitColor)
            )
            Text(
                text = index, fontFamily = Font(
                    R.font.main_bold
                ).toFontFamily(),
                color = PrimaryColor,
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 18.sp
            )
            Text(
                text = program.title,
                fontFamily = Font(
                    R.font.main_semibold
                ).toFontFamily(),
                color = PrimaryColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            )
            HeartButton(modifier = Modifier.padding(end = 8.dp), isSelected = isFav, onClick = {
                onFavClick.invoke(it, program)
            })
//            Image(
//                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
//                contentDescription = ""
//            )
        }
    }
}



