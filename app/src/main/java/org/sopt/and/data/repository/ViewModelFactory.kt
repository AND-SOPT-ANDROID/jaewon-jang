package org.sopt.and.data.repository

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.and.presentation.auth.viewmodel.SignInViewModel
import org.sopt.and.presentation.auth.viewmodel.SignUpViewModel
import org.sopt.and.presentation.main.viewmodel.MypageViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val context: Context // Context 추가
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(authRepository, context) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(authRepository, context) as T
            }
            modelClass.isAssignableFrom(MypageViewModel::class.java) -> {
                MypageViewModel(authRepository, context) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}