package com.surajrathod.bcawave.ui.programdetails.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.surajrathod.bcawave.R


@Composable
fun CommonToggleButton(
    modifier: Modifier,
    isSelected: Boolean,
    @DrawableRes selectedIcon: Int = R.drawable.ic_baseline_favorite_24,
    @DrawableRes unselectedIcon: Int = R.drawable.ic_baseline_favorite_border_24,
    onClick: (Boolean) -> Unit
) {

    //var checked by remember { mutableStateOf(isSelected) }

    Box(modifier = modifier
        .clickable {
            //checked = !checked
            //onClick.invoke(checked)
        }) {
        if (isSelected) {
            Image(
                painter = painterResource(id = selectedIcon),
                contentDescription = ""
            )
        } else {
            Image(
                painter = painterResource(id = unselectedIcon),
                contentDescription = ""
            )
        }
    }

}