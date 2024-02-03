package com.surajrathod.bcawave.ui.dashboard.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.surajrathod.bcawave.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_baseline_home_24
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_baseline_share_24
    )

    object Programs : BottomBarScreen(
        route = "programs",
        title = "Programs",
        icon = R.drawable.ic_baseline_favorite_24
    )
}
