package com.surajrathod.bcawave.ui.dashboard.programs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.HomeViewModel
import com.surajrathod.bcawave.ui.dashboard.home.components.ProgramItem
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor


@Composable
fun ProgramsScreen(
    homeViewModel: HomeViewModel,
    lazyListState: LazyListState,
    paddingValues: PaddingValues
) {
    val programs = homeViewModel.localPrograms.observeAsState().value?.map {
        it.toProgramItemData(true)
    }
    Surface(
        color = PrimaryBgColor, modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Column {
            Column(
                Modifier
                    .background(PrimaryColor)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
            ) {
                Text(
                    text = "Favourites",
                    color = Color.White,
                    modifier = Modifier.padding(start = 6.dp),
                    fontFamily = Font(
                        R.font.main_bold
                    ).toFontFamily(),
                    fontSize = 18.sp
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = lazyListState
            ) {
                if (!programs.isNullOrEmpty()) {
                    itemsIndexed(programs, key = { _, item ->
                        item.id
                    }) { index, item ->
                        ProgramItem(isFav = item.isFav,
                            program = item,
                            index = (index + 1).toString(),
                            onFavClick = { isFav, program ->
                                if (isFav) {
                                    homeViewModel.addToFavourite(program)
                                } else {
                                    homeViewModel.removeFromFav(program.id)
                                }
                            }) { program ->
                            //onProgramItemClick.invoke(program.toProgram())
                        }
                    }
                }

            }
        }

    }
}