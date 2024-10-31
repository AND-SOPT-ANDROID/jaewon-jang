package org.sopt.and.presentation.common

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

// SocialLoginIcon(SignIn, SignUp)
@Composable
fun KakaoLoginIcon(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.kakao1),
            contentDescription = "Kakao",
            tint = Color.Unspecified,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun NaverLoginIcon(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.naver1),
            contentDescription = "Naver",
            tint = Color.Unspecified,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun FacebookLoginIcon(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.facebook1),
            contentDescription = "Facebook",
            tint = Color.Unspecified,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun AppleLoginIcon(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.apple1),
            contentDescription = "Apple",
            tint = Color.Unspecified,
            modifier = Modifier.size(48.dp)
        )
    }
}

//로그인 버튼(SignIn)
@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text("로그인", fontSize = 18.sp, color = Color.White)
    }
}