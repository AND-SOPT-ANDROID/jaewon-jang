package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*  // 레이아웃 및 박스
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*  // Material3 사용
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 예시로 이메일 값을 전달합니다.
        val email = intent.getStringExtra("email") ?: "unknown@example.com" // 회원가입 시 받은 이메일

        setContent {
            MyScreenContent(email)
        }
    }
}


@Composable
fun MyScreenContent(email: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // 상단 프로필 섹션
        ProfileSection(email)

        Spacer(modifier = Modifier.height(24.dp))

        // 전체 시청 내역 섹션
        EmptySection(title = "전체 시청내역", message = "시청내역이 없어요.")

        Spacer(modifier = Modifier.height(24.dp))

        // 관심 프로그램 섹션
        EmptySection(title = "관심 프로그램", message = "관심 프로그램이 없어요.")
    }
}

@Composable
fun ProfileSection(email: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 이미지 대신 색상만 사용하여 원형 배경 표시
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Blue)  // 파란색 원형 배경
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            // 이메일이 닉네임으로 표시됨
            Text(
                text = email,  // 닉네임으로 이메일 사용
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 우측 알림 및 설정 아이콘 (Compose 기본 아이콘 사용)
        IconButton(onClick = { /* 알림 버튼 동작 */ }) {
            Icon(
                imageVector = Icons.Default.Notifications,  // 기본 알림 아이콘 사용
                contentDescription = "알림",
                tint = Color.White
            )
        }
        IconButton(onClick = { /* 설정 버튼 동작 */ }) {
            Icon(
                imageVector = Icons.Default.Settings,  // 기본 설정 아이콘 사용
                contentDescription = "설정",
                tint = Color.White
            )

        }
    }
    Spacer(modifier = Modifier.height(20.dp))

    Column {
        Text(
            text = "첫 결제 시 첫 달 100원!",
            fontSize = 20.sp,
            color = Color.Gray
        )
    }
    TextButton(onClick = { /* 구매하기 동작 */ }) {
        Text(text = "구매하기>", color = Color.White, textAlign = TextAlign.Left)
    }
}


@Composable
fun EmptySection(title: String, message: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray, CircleShape),

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
    MyScreenContent(email = "unknown@example.com")
}