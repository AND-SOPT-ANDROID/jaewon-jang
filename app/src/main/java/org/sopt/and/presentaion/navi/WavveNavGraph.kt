package org.sopt.and.presentaion.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.sopt.and.presentaion.main.screen.MainScreen
import org.sopt.and.presentaion.auth.screen.SignInScreen
import org.sopt.and.presentaion.auth.screen.SignUpScreen

fun NavGraphBuilder.wavveNavGraph(
    navController: NavHostController,
) {
    composable(route = "sign_in") {
        SignInScreen(
            navigateToSignUp = { navController.navigateSignUp() },
            navigateToMain = { navController.navigateMain() }
        )
    }
    composable(route = "sign_up") {
        SignUpScreen(
            navigateToSignIn = { navController.navigateSignIn() }
        )
    }
    composable(route = "main") {
        MainScreen()
    }
}