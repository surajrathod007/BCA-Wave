package com.surajrathod.bcawave.ui.dashboard.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.surajrathod.bcawave.ui.dashboard.home.HomeScreen
import com.surajrathod.bcawave.ui.dashboard.home.HomeViewModel
import com.surajrathod.bcawave.ui.dashboard.profile.ProfileScreen
import com.surajrathod.bcawave.ui.dashboard.programs.ProgramsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    paddings: PaddingValues
) {
    val localProgramsLazyListState = rememberLazyListState()
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen(homeViewModel, paddings) { program ->

            }
        }
        composable(
            route = BottomBarScreen.Programs.route
        ) {
            ProgramsScreen(homeViewModel, localProgramsLazyListState)
        }
        composable(
            route = BottomBarScreen.Profile.route
        ) {
            ProfileScreen()
        }
    }

}