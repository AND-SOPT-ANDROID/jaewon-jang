package org.sopt.and.presentation.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.sopt.and.presentation.main.screen.MainScreen
import org.sopt.and.presentation.auth.screen.SignInScreen
import org.sopt.and.presentation.auth.screen.SignUpScreen

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