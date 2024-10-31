package org.sopt.and.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.R
import org.sopt.and.Common.validateInput
import org.sopt.and.presentation.common.AppleLoginIcon
import org.sopt.and.presentation.common.FacebookLoginIcon
import org.sopt.and.presentation.common.KakaoLoginIcon
import org.sopt.and.presentation.common.NaverLoginIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
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

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("이메일 주소 또는 아이디") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.DarkGray,
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(if (passwordVisible) "Hide" else "Show")
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.DarkGray,
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (validateInput(context, id, password)) {
                    onSignInClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("로그인", fontSize = 18.sp, color = Color.White)
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
