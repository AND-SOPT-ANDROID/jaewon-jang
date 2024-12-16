package org.sopt.and.presentation.main.contract

sealed class MypageIntent {
    data object FetchHobby : MypageIntent()
}

data class MypageState(
    val hobby: String? = null,
    val errorMessage: String? = null
)
