package org.sopt.and.presentation.auth.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.auth.viewmodel.SignInViewModel
import org.sopt.and.presentation.auth.component.SignInSignUpButton
import org.sopt.and.presentation.auth.component.SignInSignUpTextField
import org.sopt.and.presentation.auth.component.SocialLoginIcon
import org.sopt.and.data.repository.AuthRepositoryImpl
import org.sopt.and.data.api.ServicePool
import org.sopt.and.data.repository.ViewModelFactory
import org.sopt.and.presentation.auth.contract.SignInIntent


@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,

) {
    val context = LocalContext.current
    val authRepository = AuthRepositoryImpl(ServicePool.apiService)
    val factory = ViewModelFactory(authRepository, context)
    val viewModel: SignInViewModel =
        androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.token) {
        state.token?.let { token ->
            context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .apply()
            navigateToMain()
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Wavve Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        SignInSignUpTextField(
            label = "Username",
            value = state.username,
            onValueChange = { viewModel.sendIntent(SignInIntent.UpdateUsername(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInSignUpTextField(
            label = "Password",
            value = state.password,
            onValueChange = { viewModel.sendIntent(SignInIntent.UpdatePassword(it)) },
            isPassword = true,
            passwordVisible = false,
            onPasswordVisibilityChange = { }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignInSignUpButton(
            text = "로그인",
            backgroundColor = Color.Blue,
            onClick = {
                viewModel.sendIntent(SignInIntent.SignIn)
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
    }

    if (state.token != null) {
        LaunchedEffect(state.token) {
            context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit()
                .putString("token", state.token)
                .apply()
            navigateToMain()
        }

        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}




