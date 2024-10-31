package org.sopt.and.navi

import androidx.navigation.NavController

fun NavController.navigateSignIn() {
    navigate("sign_in")
}

fun NavController.navigateSignUp() {
    navigate(route = "sign_up")
}

fun NavController.navigateMain() {
    navigate("main")
}

