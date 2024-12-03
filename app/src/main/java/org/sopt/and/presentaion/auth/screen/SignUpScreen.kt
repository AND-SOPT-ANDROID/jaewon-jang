package org.sopt.and.presentaion.auth.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.presentaion.auth.viewmodel.SignUpViewModel
import org.sopt.and.presentaion.auth.component.SignInSignUpTextField
import org.sopt.and.presentaion.auth.component.SignInSignUpButton
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.presentaion.auth.component.SocialLoginIcon

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    val username by viewModel.username.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val hobby by viewModel.hobby.observeAsState("")
    val context = LocalContext.current
    val message by viewModel.message.observeAsState()
    val userNumber by viewModel.userNumber.observeAsState()


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
            text = "이름과 비밀번호,취미 입력만으로\nWavve를 즐길 수 있어요!",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInSignUpTextField(
            label = "Username",
            value = username,
            onValueChange = { viewModel.updateUsername(it) },
            containerColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.DarkGray
        )


        Text(
            text = "이름은 8자 이하로 입력해주세요.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInSignUpTextField(
            label = "Wavve 비밀번호 설정",
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
            containerColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.DarkGray
        )


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "비밀번호는 8자 이하로 입력해주세요.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInSignUpTextField(
            label = "Hobby",
            value = hobby,
            onValueChange = { viewModel.updateHobby(it) },
            containerColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "취미는 8자 이하로 입력해주세요.",
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
            SocialLoginIcon(
                iconResId = R.drawable.kakao1,
                contentDescription = "Kakao"
            ) { /* Kakao 로그인 동작 */ }
            SocialLoginIcon(
                iconResId = R.drawable.naver1,
                contentDescription = "Naver"
            ) { /* Naver 로그인 동작 */ }
            SocialLoginIcon(
                iconResId = R.drawable.facebook1,
                contentDescription = "Facebook"
            ) { /* Facebook 로그인 동작 */ }
            SocialLoginIcon(
                iconResId = R.drawable.apple1,
                contentDescription = "Apple"
            ) { /* Apple 로그인 동작 */ }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier.fillMaxWidth()) {
            SignInSignUpButton(
                text = "Wavve 회원가입",
                backgroundColor = Color.Gray,
                onClick = {
                    viewModel.signUp()
                    message?.let { msg ->
                        if (msg == "회원가입 성공") {
                            userNumber?.let { number ->
                                Toast.makeText(context, "회원가입 성공! 유저 번호: $number", Toast.LENGTH_SHORT).show()
                                navigateToSignIn()
                            }
                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navigateToSignIn = {})
}