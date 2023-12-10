package com.surajrathod.bcawave.ui.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.surajrathod.bcawave.ui.dashboard.home.HomeScreen
import com.surajrathod.bcawave.ui.dashboard.profile.ProfileScreen
import com.surajrathod.bcawave.ui.dashboard.programs.ProgramsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen()
        }
        composable(
            route = BottomBarScreen.Programs.route
        ) {
            ProgramsScreen()
        }
        composable(
            route = BottomBarScreen.Profile.route
        ) {
            ProfileScreen()
        }
    }

}