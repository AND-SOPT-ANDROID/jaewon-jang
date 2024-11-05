package org.sopt.and.auth.component

import androidx.compose.runtime.Composable
import org.sopt.and.auth.screen.CustomOutlinedTextField


//로그인 등록 필드(SignUp)
@Composable
fun EmailInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = "wavve@example.com",
        passwordVisible = true // 이메일 입력은 항상 보이도록 설정
    )
}