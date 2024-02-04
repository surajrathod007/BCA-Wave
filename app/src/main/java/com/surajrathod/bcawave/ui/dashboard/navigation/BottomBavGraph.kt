package com.surajrathod.bcawave.ui.dashboard.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.surajrathod.bcawave.ui.dashboard.home.HomeScreen
import com.surajrathod.bcawave.ui.dashboard.home.HomeViewModel
import com.surajrathod.bcawave.ui.dashboard.profile.ProfileScreen
import com.surajrathod.bcawave.ui.dashboard.programs.ProgramsScreen
import com.surajrathod.bcawave.ui.programdetails.ProgramDetailsScreen
import com.surajrathod.bcawave.ui.programdetails.ProgramDetailsViewModel
import com.surajrathod.bcawave.utils.ScreenRoutes

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    programDetailsViewModel: ProgramDetailsViewModel,
    paddings: PaddingValues
) {
    val localProgramsLazyListState = rememberLazyListState()
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen(homeViewModel, paddings) { program ->
                navController.navigate("${ScreenRoutes.PROGRAM_DETAILS_ROUTE}/${program.id}")
            }
        }
        composable(
            route = BottomBarScreen.Programs.route
        ) {
            ProgramsScreen(homeViewModel, localProgramsLazyListState, paddings)
        }
        composable(
            route = BottomBarScreen.Profile.route
        ) {
            ProfileScreen()
        }
        composable(
            "${ScreenRoutes.PROGRAM_DETAILS_ROUTE}/{programId}",
            arguments = listOf(navArgument("programId") {
                type = NavType.StringType
            }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val programId = backStackEntry.arguments?.getString("programId") ?: ""
            ProgramDetailsScreen(programId, programDetailsViewModel) {
                programDetailsViewModel.clearCurrentProgram()
                navController.popBackStack()
            }
        }
    }

}