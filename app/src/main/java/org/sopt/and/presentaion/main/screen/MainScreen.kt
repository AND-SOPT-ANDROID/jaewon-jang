package org.sopt.and.presentaion.main.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = { paddingValues ->
            NavHostContainer(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
            label = { Text("홈", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            },
            label = { Text("검색", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("search") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "MY", tint = Color.White) },
            label = { Text("MY", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("mypage") }
        )
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(paddingValues = PaddingValues(0.dp)) }
        composable("search") { SearchScreen(paddingValues = PaddingValues(0.dp)) }
        composable("mypage") { MypageScreen() }
    }
}