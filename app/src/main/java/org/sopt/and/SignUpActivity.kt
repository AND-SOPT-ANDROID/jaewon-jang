package org.sopt.and

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpActivity : ComponentActivity() {

    companion object {
        val PASSWORD_REGEX =
            Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,20}\$")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "signup") {
                composable("signup") {
                    SignUpScreen(navController)
                }
                composable("signin") {
                    SignInScreen()
                }
            }
        }
    }

    class SignUpViewModel : ViewModel() {
        private val _id = MutableStateFlow("")
        val id: StateFlow<String> = _id

        private val _password = MutableStateFlow("")
        val password: StateFlow<String> = _password

        fun setId(newId: String) {
            _id.value = newId
        }

        fun setPassword(newPassword: String) {
            _password.value = newPassword
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SignUpScreen(navController: NavHostController, viewModel: SignUpViewModel = viewModel()) {
        val id by viewModel.id.collectAsState()
        val password by viewModel.password.collectAsState()
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

            CustomOutlinedTextField(
                value = id,
                onValueChange = { viewModel.setId(it) },
                label = "wavve@example.com"
            )

            Text(
                text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = password,
                onValueChange = { viewModel.setPassword(it) },
                label = "Wavve 비밀번호 설정",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.kakao1),
                    contentDescription = "Kakao",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.naver1),
                    contentDescription = "Naver",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.facebook1),
                    contentDescription = "Facebook",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.apple1),
                    contentDescription = "Apple",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        if (validateInput(id, password)) {
                            navController.navigate("signin")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Wavve 회원가입", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }

    private fun validateInput(id: String, password: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
                showToast("유효한 이메일을 입력하세요.")
                false
            }

            !PASSWORD_REGEX.matches(password) -> {
                showToast("비밀번호는 8~20자 이내로 대소문자, 숫자, 특수문자 조합이어야 합니다.")
                false
            }

            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun SignInScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "로그인 화면", color = Color.White, fontSize = 24.sp)
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
    fun PreviewSignUpScreen() {
        val navController = rememberNavController()
        SignUpScreen(navController)
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSignInScreen() {
        SignInScreen()
    }
}