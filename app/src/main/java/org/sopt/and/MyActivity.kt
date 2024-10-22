package org.sopt.and

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

class MyActivity : ComponentActivity() {

    companion object {
        const val EXTRA_EMAIL = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra(EXTRA_EMAIL)
        if (email == null) {
            Toast.makeText(this, "이메일 정보가 없습니다!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            MyApp(email)
        }
    }

    // 앱의 시작점 설정 및 네비게이션 초기화
    @Composable
    fun MyApp(email: String) {
        val navController = rememberNavController()
        NavGraph(navController = navController, email = email)
    }

    // 네비게이션 그래프 설정
    @Composable
    fun NavGraph(navController: NavHostController, email: String) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                MyScreenContent(email = email)
            }
            composable("settings") {
                SettingsScreen()
            }
        }
    }

    // ViewModel을 사용한 MyScreenContent 화면
    @Composable
    fun MyScreenContent(
        email: String,
        viewModel: MyViewModel = viewModel()
    ) {
        // StateFlow를 collectAsState로 수집하여 UI에서 사용할 수 있도록 변환
        val profileData by viewModel.profileData.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            ProfileSection(email = email, profileData = profileData)

            Spacer(modifier = Modifier.height(24.dp))

            ContentSection(title = "전체 시청내역", message = "시청내역이 없어요.")

            Spacer(modifier = Modifier.height(24.dp))

            ContentSection(title = "관심 프로그램", message = "관심 프로그램이 없어요.")
        }
    }

    // 프로필 섹션
    @Composable
    fun ProfileSection(email: String, profileData: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = email,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { /* 알림 버튼 동작 */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "알림",
                    tint = Color.White
                )
            }
            IconButton(onClick = { /* 설정 버튼 동작 */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "설정",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "프로필 정보: $profileData", // StateFlow로부터 받은 프로필 데이터
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

        TextButton(
            onClick = { /* 구매하기 동작 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "구매하기>", color = Color.White, textAlign = TextAlign.Left)
        }
    }

    // 콘텐츠 섹션
    @Composable
    fun ContentSection(title: String, message: String) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }

    // 설정 화면
    @Composable
    fun SettingsScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "설정 화면",
                color = Color.White,
                fontSize = 25.sp
            )
        }
    }

    // ViewModel을 사용한 상태 관리
    class MyViewModel : ViewModel() {
        // 프로필 데이터를 관리하는 StateFlow
        private val _profileData = MutableStateFlow("프로필 정보")
        val profileData: StateFlow<String> = _profileData

        // 프로필 데이터를 업데이트하는 함수
        fun updateProfile(newData: String) {
            _profileData.value = newData
        }
    }

    // Preview를 위한 MyScreenContent 미리보기
    @Preview(showBackground = true)
    @Composable
    fun PreviewMyScreen() {
        MyScreenContent(email = "unknown@example.com")
    }

    // Preview를 위한 ProfileSection 미리보기
    @Preview(showBackground = true)
    @Composable
    fun PreviewProfileSection() {
        ProfileSection(email = "example@example.com", profileData = "프로필 정보")
    }
}
