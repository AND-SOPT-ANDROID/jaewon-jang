package org.sopt.and

import android.content.Intent
import android.os.Bundle
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

class SignInActivity : ComponentActivity() {
    private var registeredId: String? = null
    private var registeredPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 회원가입으로부터 데이터를 받기 위한 Intent
        registeredId = intent.getStringExtra("ID")
        registeredPassword = intent.getStringExtra("Password")

        setContent {
            SignInScreen(
                onSignUpClick = {
                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                },
                onLoginClick = { id, password ->
                    if (id == registeredId && password == registeredPassword) {
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MyActivity::class.java).apply {
                            putExtra("email", id)  // 이메일을 MyActivity로 전달
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: (String, String) -> Unit
) {
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

        Text(
            text = "Wavve",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(60.dp))


        // 이메일 입력 필드
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("이메일 주소 또는 아이디") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.DarkGray, // 배경 색상 설정
                focusedLabelColor = Color.LightGray, // 포커스된 라벨 색상
                unfocusedLabelColor = Color.Gray // 비포커스된 라벨 색상
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력 필드
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
                containerColor = Color.DarkGray, // 배경 색상 설정
                focusedLabelColor = Color.LightGray, // 포커스된 라벨 색상
                unfocusedLabelColor = Color.Gray // 비포커스된 라벨 색상
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 로그인 버튼
        Button(
            onClick = { onLoginClick(id, password) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("로그인", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 회원가입 버튼
        TextButton(onClick = onSignUpClick) {
            Text(text = "회원가입", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        onSignUpClick = {},
        onLoginClick = { _, _ -> }
    )
}