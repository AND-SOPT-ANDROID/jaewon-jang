package org.sopt.and.presentaion.auth.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.R
import org.sopt.and.presentaion.auth.viewmodel.SignInViewModel
import org.sopt.and.presentaion.auth.component.SignInSignUpButton
import org.sopt.and.presentaion.auth.component.SignInSignUpTextField
import org.sopt.and.presentaion.auth.component.SocialLoginIcon
import org.sopt.and.data.repository.AuthRepositoryImpl
import org.sopt.and.data.api.ServicePool


@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,
) {
    val apiService = ServicePool.apiService
    val authRepository = AuthRepositoryImpl(apiService)
    val viewModel = remember { SignInViewModel(authRepository) }

    val context = LocalContext.current
    val username by viewModel.username.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val token by viewModel.token.observeAsState()
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
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Wavve Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        SignInSignUpTextField(
            label = "Username",
            value = username,
            onValueChange = { viewModel.updateUsername(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInSignUpTextField(
            label = "Password",
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignInSignUpButton(
            text = "로그인",
            backgroundColor = Color.Blue,
            onClick = {
                viewModel.signIn()
                if (token != null) {
                    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("token", token).apply()

                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                } else {
                    errorMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )


        Spacer(modifier = Modifier.height(5.dp))

        TextButton(onClick = navigateToSignUp) {
            Text(text = "회원가입", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "또는 다른 서비스 계정으로 로그인",
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        navigateToSignUp = {},
        navigateToMain = {}
    )
}