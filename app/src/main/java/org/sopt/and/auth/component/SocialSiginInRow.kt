package org.sopt.and.auth.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

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