package com.surajrathod.bcawave.ui.dashboard.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.HomeViewModel
import com.surajrathod.bcawave.ui.dashboard.home.makeSelection
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor

@Composable
fun RowScope.GenericSpinner(
    menuItems: List<String>? = mutableListOf("Suraj", "Temp"),
    currentSelection: String?,
    homeViewModel: HomeViewModel,
    myModifier: Modifier = Modifier
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = myModifier
        .weight(1f, true)
        .clickable {
            expanded = !expanded
        }) {
        Box(
            modifier = Modifier
                .clip(CircleShape.copy(CornerSize(6.dp)))
                .background(PrimaryBgColor)
        ) {
            Text(
                text = currentSelection ?: "",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                fontFamily = Font(
                    R.font.main_bold
                ).toFontFamily(),
                color = PrimaryColor,
                textAlign = TextAlign.Center
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                menuItems?.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it, fontFamily = Font(
                                    R.font.main_bold
                                ).toFontFamily(),
                                color = PrimaryColor
                            )
                        },
                        onClick = {
                            expanded = false
                            makeSelection(it, homeViewModel)
                        },
                    )
                }
            }
        }
    }


}