package com.surajrathod.bcawave.ui.programdetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.surajrathod.bcawave.ui.dashboard.home.HomeViewModel
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.ProgramItemData
import com.surajrathod.bcawave.ui.dashboard.home.components.ComposeLottieAnimation
import com.surajrathod.bcawave.ui.programdetails.components.CommonTextBadge
import com.surajrathod.bcawave.ui.programdetails.components.CommonToggleButton
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor
import com.surajrathod.bcawave.ui.theme.SemColor
import com.surajrathod.bcawave.ui.theme.SubColor
import com.surajrathod.bcawave.ui.theme.UnitColor
import com.surajrathod.bcawave.ui.utils.components.HeartButton


@Composable
fun ProgramDetailsScreen(
    programId: String = "",
    programDetailsViewModel: ProgramDetailsViewModel,
    onBackPress: () -> Unit = {}
) {

    val program = programDetailsViewModel.currentProgram.observeAsState()
    LaunchedEffect(programId) {
        //Log.d("SURAJPROGRAM", "Programid : $programId")
        //programDetailsViewModel.getFirestorePrograms("Sem 1", "IPLC", "Unit 1")'
        programDetailsViewModel.getProgramById(programId)
    }

    Log.d("SURAJPROGRAM", "IsFav : ${program.value?.isFav}")
    //Log.d("SURAJPROGRAM", "Compose program : ${programDetailsViewModel.currentProgram.value}")
    Column(
        Modifier
            .fillMaxSize()
            .background(PrimaryBgColor)
    ) {
        TopNavigation(program.value?.isFav ?: false, onFavButtonClick = {}) {
            onBackPress.invoke()
        }
        if (programDetailsViewModel.isLoading.value) {
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
            Column {
                Text(
                    text = "${program.value?.title}", fontFamily = Font(
                        R.font.main_semibold
                    ).toFontFamily(),
                    color = PrimaryColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
                Row(
                    modifier = Modifier.padding(start = 12.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CommonTextBadge(
                        modifier = Modifier,
                        text = "${program.value?.sem}",
                        textColor = SemColor,
                    )
                    CommonTextBadge(
                        modifier = Modifier,
                        text = "${program.value?.sub}",
                        textColor = SubColor,
                    )
                    CommonTextBadge(
                        modifier = Modifier,
                        text = "${program.value?.unit}",
                        textColor = UnitColor,
                    )
                }

            }
        }
    }
}


@Composable
fun MyPreview() {
    Column(Modifier.fillMaxSize()) {
        TopNavigation(false, onFavButtonClick = {}) {
            //onBackPress.invoke()
        }
        Column {
            Text(
                text = "Helllo", fontFamily = Font(
                    R.font.main_semibold
                ).toFontFamily(),
                color = PrimaryColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .weight(1f)
            )
        }
    }
}


@Composable
fun TopNavigation(
    isFav: Boolean = false,
    onFavButtonClick: (Boolean) -> Unit,
    onBackButtonClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(PrimaryColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clickable {
                    onBackButtonClick.invoke()
                }
                .padding(vertical = 12.dp, horizontal = 8.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = ""
        )
        CommonToggleButton(
            modifier = Modifier.padding(horizontal = 8.dp),
            isSelected = isFav,
            unselectedIcon = R.drawable.ic_fav_border_white,
            onClick = onFavButtonClick
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProgramDetailsScreenPreview(
    programId: String = ""
) {
    MyPreview()
}