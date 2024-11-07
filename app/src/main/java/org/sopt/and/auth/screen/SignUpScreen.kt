package org.sopt.and.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.auth.viewmodel.SignUpViewModel
import org.sopt.and.auth.component.SignUpEmailInputField
import org.sopt.and.auth.component.SignUpPasswordInputField
import org.sopt.and.auth.component.SignUpButton
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.auth.component.SocialLoginIcon

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    val id by viewModel.id.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState()

    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "회원가입",
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "이메일과 비밀번호만으로\nWavve를 즐길 수 있어요!",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignUpEmailInputField(
            value = id,
            onValueChange = { viewModel.updateId(it) }
        )

        Text(
            text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignUpPasswordInputField(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "또는 다른 서비스 계정으로 가입",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            SocialLoginIcon(iconResId = R.drawable.kakao1, contentDescription = "Kakao") { /* Kakao 로그인 동작 */ }
            SocialLoginIcon(iconResId = R.drawable.naver1, contentDescription = "Naver") { /* Naver 로그인 동작 */ }
            SocialLoginIcon(iconResId = R.drawable.facebook1, contentDescription = "Facebook") { /* Facebook 로그인 동작 */ }
            SocialLoginIcon(iconResId = R.drawable.apple1, contentDescription = "Apple") { /* Apple 로그인 동작 */ }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier.fillMaxWidth()) {
            SignUpButton(onClick = {
                viewModel.signUp {
                    navigateToSignIn()
                }
            })
        }


        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = Color.Red)
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = onPasswordVisibilityChange?.let {
            {
                TextButton(onClick = onPasswordVisibilityChange) {
                    Text(if (passwordVisible) "Hide" else "Show")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navigateToSignIn = {})
}

