package org.sopt.and.presentation.auth.screen

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
import org.sopt.and.presentation.auth.viewmodel.SignUpViewModel
import org.sopt.and.presentation.auth.component.SignInSignUpTextField
import org.sopt.and.presentation.auth.component.SignInSignUpButton
import org.sopt.and.R
import org.sopt.and.data.api.ServicePool
import org.sopt.and.data.repository.AuthRepositoryImpl
import org.sopt.and.data.repository.ViewModelFactory
import org.sopt.and.presentation.auth.component.SocialLoginIcon
import org.sopt.and.presentation.auth.contract.SignUpIntent

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
) {
    val context = LocalContext.current
    val authRepository = AuthRepositoryImpl(ServicePool.apiService)
    val factory = ViewModelFactory(authRepository, context)
    val viewModel: SignUpViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.successMessage) {
        state.successMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearSuccessMessage()
            navigateToSignIn()
        }
    }

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
            value = state.username,
            onValueChange = { viewModel.sendIntent(SignUpIntent.UpdateUsername(it)) }
        )


        Text(
            text = "이름은 8자 이하로 입력해주세요.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInSignUpTextField(
            label = "Password",
            value = state.password,
            onValueChange = { viewModel.sendIntent(SignUpIntent.UpdatePassword(it)) },
            isPassword = true
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
            value = state.hobby,
            onValueChange = { viewModel.sendIntent(SignUpIntent.UpdateHobby(it)) }
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
                onClick = { viewModel.sendIntent(SignUpIntent.SignUp) }
            )

            state.successMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                navigateToSignIn()
            }

            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

        }

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navigateToSignIn = {})
}