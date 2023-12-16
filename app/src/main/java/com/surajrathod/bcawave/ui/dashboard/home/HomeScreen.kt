package com.surajrathod.bcawave.ui.dashboard.home


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    Surface(color = PrimaryBgColor, modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(PrimaryBgColor)
        ) {
            Column(Modifier.background(PrimaryColor)) {
                Text(
                    text = "Home",
                    color = Color.White,
                    modifier = Modifier.padding( start = 6.dp, top = 6.dp),
                    fontFamily = Font(
                        R.font.main_bold
                    ).toFontFamily(),
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryColor)
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    GenericSpinner(
                        homeViewModel.semList,
                        homeViewModel.currentSem.value,
                        homeViewModel,
                        Modifier.padding(start = 6.dp, end = 3.dp)
                    )
                    GenericSpinner(
                        homeViewModel.subjectList.value,
                        homeViewModel.currentSubject.value,
                        homeViewModel,
                        Modifier.padding(start = 3.dp, end = 3.dp)
                    )
                    GenericSpinner(
                        homeViewModel.unitList,
                        homeViewModel.currentUnit.value,
                        homeViewModel,
                        Modifier.padding(start = 3.dp, end = 6.dp)
                    )
                }
            }

        }
    }
}


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

fun makeSelection(item: String, homeViewModel: HomeViewModel) {
    //user selected from sem
    if (homeViewModel.semList.contains(item)) {
        homeViewModel.currentSem.value = item
        if (homeViewModel.subjectmutableMap.isNotEmpty()) {
            for ((key, value) in homeViewModel.subjectmutableMap) {
                if (key == homeViewModel.semList.indexOf(item) + 1) {
                    homeViewModel.subjectList.value = homeViewModel.subjectmutableMap[key]
                    homeViewModel.currentSubject.value = value.firstOrNull() ?: ""
                }
            }
        }
    }
    //user selected from subject
    if (isFromSubjectSelection(item, homeViewModel)) {
        homeViewModel.currentSubject.value = item
        homeViewModel.currentUnit.value = homeViewModel.unitList[0] //set unit 1 again
    }
    //user selected from unit
    if (isFromUnitSelection(item, homeViewModel)) {
        homeViewModel.currentUnit.value = item
    }
}

private fun isFromSubjectSelection(item: String, homeViewModel: HomeViewModel): Boolean {
    if (homeViewModel.subjectmutableMap.isNotEmpty()) {
        for ((_, value) in homeViewModel.subjectmutableMap) {
            if (value.contains(item)) {
                return true
            }
        }
    }
    return false
}

private fun isFromUnitSelection(item: String, homeViewModel: HomeViewModel): Boolean {
    return homeViewModel.unitList.contains(item)
}


