package org.sopt.and.presentation.auth.contract

sealed class SignUpIntent {
    data class UpdateUsername(val username: String) : SignUpIntent()
    data class UpdatePassword(val password: String) : SignUpIntent()
    data class UpdateHobby(val hobby: String) : SignUpIntent()
    data object SignUp : SignUpIntent()
}

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val hobby: String = "",
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
