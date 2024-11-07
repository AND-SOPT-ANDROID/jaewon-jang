package org.sopt.and.auth.screen

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
import org.sopt.and.auth.viewmodel.SignInViewModel
import org.sopt.and.auth.component.SignInLoginButton
import org.sopt.and.auth.component.SignInLoginField
import org.sopt.and.auth.component.SignInPasswordField
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.auth.component.SocialLoginIcon


@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: SignInViewModel = viewModel()
) {
    val context = LocalContext.current
    val id by viewModel.id.observeAsState("")  // observeAsState를 통해 LiveData 값을 가져옵니다.
    val password by viewModel.password.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState()  // LiveData 관찰

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

        SignInLoginField(
            value = id,
            onValueChange = { viewModel.updateId(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignInPasswordField(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignInLoginButton {
            if (viewModel.isValidInput()) {
                navigateToMain()
            } else {
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

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
