package com.surajrathod.bcawave.ui.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.surajrathod.bcawave.R

@Composable
fun HeartButton(modifier: Modifier, isSelected: Boolean, onClick: (Boolean) -> Unit) {

    val isFav by remember {
        derivedStateOf {
            mutableStateOf(isSelected)
        }
    }


    Box(modifier = modifier
        .clickable {
            isFav.value = !isFav.value
            onClick.invoke(isFav.value)
        }) {
        if (isFav.value) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                contentDescription = ""
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
                contentDescription = ""
            )
        }
    }

}