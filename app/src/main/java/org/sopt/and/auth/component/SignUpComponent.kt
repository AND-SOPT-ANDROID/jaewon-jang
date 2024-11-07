package org.sopt.and.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.auth.screen.CustomOutlinedTextField


@Composable
fun SignUpEmailInputField(
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

@Composable
fun SignUpButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Wavve 회원가입", fontSize = 18.sp, color = Color.White)
    }
}

@Composable
fun SignUpPasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = "Wavve 비밀번호 설정",
        passwordVisible = passwordVisible,
        onPasswordVisibilityChange = onPasswordVisibilityChange
    )
}