package org.sopt.and.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import org.sopt.and.presentation.CustomOutlinedTextField

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
//로그인 필드(SingIn)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("이메일 주소 또는 아이디") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.DarkGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.Gray
        )
    )
}

//비밀번호 필드(SignIn)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("비밀번호") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(onClick = onPasswordVisibilityChange) {
                Text(if (passwordVisible) "Hide" else "Show")
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.DarkGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.Gray
        )
    )
}
//회원가입 버튼(SignUp)
@Composable
fun SignUpButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Wavve 회원가입", fontSize = 18.sp, color = Color.White)
    }
}

//비밀번호 등록 필드(SignUp)
@Composable
fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = "Wavve 비밀번호 설정",
        passwordVisible = passwordVisible,
        onPasswordVisibilityChange = onPasswordVisibilityChange
    )
}

//로그인 등록 필드(SignUp)
@Composable
fun EmailInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = "wavve@example.com",
        passwordVisible = true // 이메일 입력은 항상 보이도록 설정
    )
}

//프로필 이미지(Mypage)
@Composable
fun ProfileImage() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Blue)
    )
}
//이메일 주소(Mypage)
@Composable
fun ProfileEmail(email: String) {
    Text(
        text = email,
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}
//알림 및 설정 버튼(Mypage)
@Composable
fun ProfileActionButtons() {
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
//구매 관련 메세지(Mypage)
@Composable
fun PurchaseInfo(onPurchaseClick: () -> Unit) {
    Spacer(modifier = Modifier.height(20.dp)) // 공간 추가

    Text(
        text = "  첫 결제 시 첫 달 100원!", // 구매 안내 텍스트
        fontSize = 15.sp, // 폰트 크기
        color = Color.Gray, // 글자 색상 회색
        modifier = Modifier.fillMaxWidth() // 전체 너비를 차지
    )

    TextButton(onClick = onPurchaseClick) { // 버튼 클릭 시 구매 동작 실행
        Text(text = "구매하기>", color = Color.White, textAlign = TextAlign.Left, fontSize = 15.sp)
    }

    Text(
        text = "  현재 보유하신 이용권이 없습니다.", // 이용권 안내 텍스트
        fontSize = 15.sp, // 폰트 크기
        color = Color.Gray, // 글자 색상 회색
        modifier = Modifier.fillMaxWidth() // 전체 너비를 차지
    )

    TextButton(onClick = onPurchaseClick) { // 버튼 클릭 시 구매 동작 실행
        Text(text = "구매하기>", color = Color.White, textAlign = TextAlign.Left, fontSize = 15.sp)
    }
}
//구매 관련 메세지(Mypage)
@Composable
fun PurchaseMessage(message: String) {
    Text(
        text = message, // 메시지 텍스트
        fontSize = 15.sp, // 폰트 크기
        color = Color.Gray, // 글자 색상 회색
        modifier = Modifier.fillMaxWidth() // 전체 너비를 차지
    )
}
//구매 버튼(Mypage)
@Composable
fun PurchaseButton(onPurchaseClick: () -> Unit, buttonText: String) {
    TextButton(onClick = onPurchaseClick) { // 버튼 클릭 시 구매 동작 실행
        Text(text = buttonText, color = Color.White, textAlign = TextAlign.Left, fontSize = 15.sp) // 버튼 텍스트
    }
}

//콘텐츠 섹션(Mypage)
@Composable
fun ContentSection(title: String, message: String) {
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
// TopBar(Home)
@Composable
fun TopBar(categories: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            Text(
                text = category,
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}
//BannerView(Home)
@Composable
fun BannerView(imageResIds: List<Int>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(imageResIds.size) { index ->
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp)
                    .background(Color.DarkGray)
            ) {
                Image(
                    painter = painterResource(id = imageResIds[index]),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
//list 섹션(Home)
@Composable
fun ListSection(title: String, imageList: List<Int>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            items(imageList.size) { index ->
                ItemCard(imageResId = imageList[index])
            }
        }
    }
}
//이미지 카드(Home)
@Composable
fun ItemCard(imageResId: Int) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Recommendation Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}