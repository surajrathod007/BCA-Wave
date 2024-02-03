package com.surajrathod.bcawave.ui.dashboard.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.surajrathod.bcawave.R
import com.surajrathod.bcawave.ui.theme.PrimaryBgColor
import com.surajrathod.bcawave.ui.theme.PrimaryColor
import com.surajrathod.bcawave.ui.theme.UnitColor


enum class TAB {
    HOME, PROFILE, PROGRAM
}


@Composable
fun NewBottomNavigation(navHostController: NavHostController) {

//    val bottomBarItems = arrayOf(
//        BottomNavItem("Home", Icons.Filled.Home, TAB.HOME.name),
//        BottomNavItem("Tv", Icons.Filled.Person, TAB.PROGRAM.name),
//        BottomNavItem("Radio", Icons.Filled.AccountCircle, TAB.PROFILE.name),
//    )

    val bottomBarItems = arrayOf(
        BottomBarScreen.Home,
        BottomBarScreen.Programs,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItem by remember {
        mutableStateOf(TAB.HOME.name)
    }

    Box {

        var width by remember { mutableStateOf(0f) }
        var currentIndex by remember { mutableStateOf(0) }
        val offsetAnim by animateFloatAsState(
            targetValue = when (currentIndex) {
                1 -> width / 2f - with(LocalDensity.current) { 50.dp.toPx() }
                2 -> width - with(LocalDensity.current) { 100.dp.toPx() }
                else -> 0f
            },
            label = "",
            animationSpec = SpringSpec(dampingRatio = 0.5f, stiffness = 200f),
        )

        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    width = it.size.width.toFloat()
                },
            backgroundColor = PrimaryColor
        ) {
            val iconColor = colorResource(id = R.color.white)
            bottomBarItems.forEachIndexed { index, item ->
                AnimatedBottomNavigationItem(
                    label = item.title,
                    icon = ImageVector.vectorResource(item.icon),
                    unselectedContentColor = iconColor,
                    selectedContentColor = iconColor,
                    selected = currentDestination?.hierarchy?.any {
                        it.route == item.route
                    } ?: false, onClick = {
                        //selectedItem = item.route
                        currentIndex = index
                        navHostController.navigate(item.route) {
                            popUpTo(navHostController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }, screen = item
                )
            }
        }
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(4.dp)
                .offset(with(LocalDensity.current) { offsetAnim.toDp() }, 0.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(UnitColor)
        )
    }
}

data class BottomNavItem(
    val title: String = "",
    val icon: ImageVector = Icons.Default.Home,
    val route: String = ""
)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AnimatedBottomNavigationItem(
    screen: BottomBarScreen,
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    @SuppressLint("RememberReturnType") interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium),
    selected: Boolean
) {
//    val top by animateDpAsState(
//        targetValue = if (selected) 0.dp else 56.dp,
//        animationSpec = SpringSpec(dampingRatio = 0.5f, stiffness = 200f)
//    )

//    var selected by remember {
//        mutableStateOf(false)
//    }

    val top by animateDpAsState(
        targetValue = if (selected) 0.dp else 56.dp,
        animationSpec = SpringSpec(dampingRatio = 0.5f, stiffness = 200f), label = ""
    )
    Box(
        modifier = Modifier
            .height(56.dp)
            .padding(start = 30.dp, end = 30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                //selected = !selected
                onClick.invoke()
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            tint = selectedContentColor,
            contentDescription = null,
            modifier = Modifier
                .height(56.dp)
                .width(26.dp)
                .offset(y = top - 56.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(56.dp)
                .offset(y = top)
        ) {
            Text(
                text = label,
                fontFamily = Font(
                    R.font.main_semibold
                ).toFontFamily(),
                color = UnitColor,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}





