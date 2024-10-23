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
import androidx.compose.ui.text.font.FontWeight
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

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "signin") {
                composable("signin") {
                    SignInScreen(navController)
                }
                composable("signup") {
                    SignUpScreen()
                }
            }
        }
    }

    class SignInViewModel : ViewModel() {
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
    fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel = viewModel()) {
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
                text = "Wavve",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { viewModel.setId(it) },
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
                onValueChange = { viewModel.setPassword(it) },
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
                    if (validateInput(id, password)) {
                        navController.navigate("signup")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("로그인", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("signup") }) {
                Text(text = "회원가입", color = Color.Gray)
            }
        }
    }

    @Composable
    fun SignUpScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "회원가입 화면", color = Color.White, fontSize = 24.sp)
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

    companion object {
        val PASSWORD_REGEX =
            Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,20}\$")
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSignInScreen() {
        val navController = rememberNavController()
        SignInScreen(navController)
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSignUpScreen() {
        SignUpScreen()
    }
}
