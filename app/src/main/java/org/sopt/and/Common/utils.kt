package org.sopt.and.Common

import android.content.Context
import android.util.Patterns
import android.widget.Toast

// 확장 함수(SignIn, SignUp)
val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,20}\$")

fun validateInput(context: Context, id: String, password: String): Boolean {
    return when {
        !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
            showToast(context, "유효한 이메일을 입력하세요.")
            false
        }
        !PASSWORD_REGEX.matches(password) -> {
            showToast(context, "비밀번호는 8~20자 이내로 대소문자, 숫자, 특수문자 조합이어야 합니다.")
            false
        }
        else -> true
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
