package org.sopt.and.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MypageViewModel : ViewModel() {
    var email: String = "user@example.com" // 초기 이메일 값

    fun updateEmail(newEmail: String) {
        viewModelScope.launch {
            email = newEmail
        }
    }
}
