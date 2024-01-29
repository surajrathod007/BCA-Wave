package com.surajrathod.bcawave.ui.dashboard.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.components.ComposeLottieAnimation
import com.surajrathod.bcawave.ui.dashboard.home.components.GenericSpinner
import com.surajrathod.bcawave.ui.dashboard.home.components.LoaderText
import com.surajrathod.bcawave.ui.dashboard.home.components.ProgramItem
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    paddingValues: PaddingValues,
    onProgramItemClick: (Program) -> Unit = {}
) {

    Surface(
        color = PrimaryBgColor, modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Column {
            Box(
                Modifier
                    .background(PrimaryBgColor)
            ) {
                Column(Modifier.background(PrimaryColor)) {
                    Text(
                        text = "Home",
                        color = Color.White,
                        modifier = Modifier.padding(start = 6.dp, top = 6.dp),
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
                            {
                                makeSelection(it, homeViewModel)
                                homeViewModel.updateData()
                            },
                            Modifier.padding(start = 6.dp, end = 3.dp)
                        )
                        GenericSpinner(
                            homeViewModel.subjectList.value,
                            homeViewModel.currentSubject.value,
                            {
                                makeSelection(it, homeViewModel)
                                homeViewModel.updateData()
                            },
                            Modifier.padding(start = 3.dp, end = 3.dp)
                        )
                        GenericSpinner(
                            homeViewModel.unitList,
                            homeViewModel.currentUnit.value,
                            {
                                makeSelection(it, homeViewModel)
                                homeViewModel.updateData()
                            },
                            Modifier.padding(start = 3.dp, end = 6.dp)
                        )
                    }
                }

            }
            if (homeViewModel.isLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PrimaryBgColor),
                    contentAlignment = Alignment.Center
                ) {
//                    LoaderText(
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                    )
                    ComposeLottieAnimation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    )
                }

            } else {
                if (homeViewModel.myPrograms.value.isEmpty()) {
                    Text(text = "No programs")
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(homeViewModel.myPrograms.value) { index, item ->
                            ProgramItem(program = item, index = (index + 1).toString()) {
                                onProgramItemClick.invoke(it)
                            }
                        }
                    }
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


