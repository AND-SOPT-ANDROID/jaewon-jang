package org.sopt.and.navi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MyScreen(
    email: String,
    viewModel: MyViewModel = viewModel()
) {
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
        text = "  첫 결제 시 첫 달 100원!",
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth()
    )

    TextButton(
        onClick = { /* 구매하기 동작 */ },
    ) {
        Text(text = "구매하기>", color = Color.White, textAlign = TextAlign.Left, fontSize = 15.sp)
    }

    Text(
        text = "  현재 보유하신 이용권이 없습니다.",
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth()
    )

    TextButton(
        onClick = { /* 구매하기 동작 */ },
    ) {
        Text(text = "구매하기>", color = Color.White, textAlign = TextAlign.Left, fontSize = 15.sp)
    }
}

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

@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    MyScreen(email = "unknown@example.com")
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileSection() {
    ProfileSection(email = "example@example.com", profileData = "프로필 정보")
}

// ViewModel 관련
class MyViewModel : androidx.lifecycle.ViewModel() {
    private val _profileData = MutableStateFlow("프로필 정보")
    val profileData: StateFlow<String> = _profileData

    fun updateProfile(newData: String) {
        _profileData.value = newData
    }
}
