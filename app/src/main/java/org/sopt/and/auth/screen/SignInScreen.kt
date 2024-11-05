package org.sopt.and.auth.screen

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
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.R
import org.sopt.and.auth.viewmodel.SignInViewModel
import org.sopt.and.common.validateInput
import org.sopt.and.auth.component.AppleLoginIcon
import org.sopt.and.auth.component.FacebookLoginIcon
import org.sopt.and.auth.component.KakaoLoginIcon
import org.sopt.and.auth.component.LoginButton
import org.sopt.and.auth.component.LoginField
import org.sopt.and.auth.component.NaverLoginIcon
import org.sopt.and.auth.component.PasswordField
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    viewModel: SignInViewModel = viewModel()
) {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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

        LoginField(
            value = id,
            onValueChange = { id = it
                viewModel.id = it}
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = password,
            onValueChange = { password = it
                viewModel.password = it},
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(24.dp))

        LoginButton {
            if (validateInput(context, id, password)) {
                onSignInClick()
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        // 회원가입 버튼
        TextButton(onClick = onSignUpClick) {
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KakaoLoginIcon { /* Kakao 로그인 클릭 시 동작 */ }
            NaverLoginIcon { /* Naver 로그인 클릭 시 동작 */ }
            FacebookLoginIcon { /* Facebook 로그인 클릭 시 동작 */ }
            AppleLoginIcon { /* Apple 로그인 클릭 시 동작 */ }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        onSignUpClick = {},
        onSignInClick = {}
    )
}
