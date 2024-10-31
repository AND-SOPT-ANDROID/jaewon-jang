package org.sopt.and.navi

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.sopt.and.presentation.MainScreen
import org.sopt.and.presentation.SignInScreen
import org.sopt.and.presentation.SignUpScreen

fun NavGraphBuilder.wavveNavGraph(
    navController: NavHostController,
) {
    composable(route = "sign_in") {
        SignInScreen(
            onSignUpClick = { navController.navigateSignUp() },
            onSignInClick = { navController.navigateMain() }
        )
    }
    composable(route = "sign_up") {
        SignUpScreen(
            onSignUpClick = { navController.navigateSignIn() }
        )
    }
    composable(route = "main") {
        MainScreen()
    }
}