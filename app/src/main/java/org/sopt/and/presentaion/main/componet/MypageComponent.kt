package org.sopt.and.presentaion.main.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MypageProfileImage() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Blue)
    )
}

@Composable
fun MypageProfileEmail(email: String) {
    Text(
        text = email,
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MypageProfileActionButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
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
}

@Composable
fun MypagePurchaseMessage(message: String) {
    Text(
        text = message,
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MypagePurchaseButton(onPurchaseClick: () -> Unit, buttonText: String) {
    TextButton(onClick = onPurchaseClick) { // 버튼 클릭 시 구매 동작 실행
        Text(text = buttonText, color = Color.White, textAlign = TextAlign.Left, fontSize = 15.sp) // 버튼 텍스트
    }
}

@Composable
fun MypageContentSection(title: String, message: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
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