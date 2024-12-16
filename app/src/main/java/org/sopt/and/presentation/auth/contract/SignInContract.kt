package org.sopt.and.presentation.auth.contract

sealed class SignInIntent {
    data class UpdateUsername(val username: String) : SignInIntent()
    data class UpdatePassword(val password: String) : SignInIntent()
    data object SignIn : SignInIntent()
}

data class SignInState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val token: String? = null,
    val errorMessage: String? = null
)
