package org.sopt.and.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.auth.screen.CustomOutlinedTextField

//로그인 버튼(SignIn)
@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text("로그인", fontSize = 18.sp, color = Color.White)
    }
}
//로그인 필드(SingIn)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("이메일 주소 또는 아이디") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.DarkGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.Gray
        )
    )
}

//비밀번호 필드(SignIn)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("비밀번호") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(onClick = onPasswordVisibilityChange) {
                Text(if (passwordVisible) "Hide" else "Show")
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.DarkGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.Gray
        )
    )
}
//회원가입 버튼(SignUp)
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

//비밀번호 등록 필드(SignUp)
@Composable
fun PasswordInputField(
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