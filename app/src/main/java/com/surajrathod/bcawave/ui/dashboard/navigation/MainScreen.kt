package com.surajrathod.bcawave.ui.dashboard.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.dashboard.home.HomeViewModel
import com.surajrathod.bcawave.ui.programdetails.ProgramDetailsViewModel
import com.surajrathod.bcawave.ui.theme.PrimaryColor
import com.surajrathod.bcawave.utils.ScreenRoutes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(homeViewModel: HomeViewModel, programDetailsViewModel: ProgramDetailsViewModel) {

    val navController = rememberNavController()
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            //if current screen is not details then and then only this should be visible
            if (currentScreen != ScreenRoutes.PROGRAM_DETAILS_ROUTE) {
                NewBottomNavigation(navController)
            }
        }
    ) { paddings ->
        BottomNavGraph(
            navController = navController,
            homeViewModel,
            programDetailsViewModel = programDetailsViewModel,
            paddings
        )
    }

}


@Composable
fun BottomBar(navHostController: NavHostController) {

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Programs,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = PrimaryColor, contentColor = Color.White) {
        screens.forEach { screen ->
            if (currentDestination != null) {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }


}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination,
    navHostController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = screen.title,
                fontFamily = Font(R.font.main_regular).toFontFamily(),
                color = Color.White
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = ""
            )
        },
        selected = currentDestination.hierarchy.any {
            it.route == screen.route
        },
        onClick = {
            navHostController.navigate(screen.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }

        },
    )
}
