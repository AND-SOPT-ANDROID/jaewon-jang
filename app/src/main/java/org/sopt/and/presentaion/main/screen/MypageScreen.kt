package org.sopt.and.presentaion.main.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentaion.main.componet.MypageContentSection
import org.sopt.and.presentaion.main.componet.MypageProfileActionButtons
import org.sopt.and.presentaion.main.componet.MypageProfileEmail
import org.sopt.and.presentaion.main.componet.MypageProfileImage
import org.sopt.and.presentaion.main.componet.MypagePurchaseButton
import org.sopt.and.presentaion.main.componet.MypagePurchaseMessage
import org.sopt.and.presentaion.main.viewmodel.MypageViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MypageScreen(
    viewModel: MypageViewModel = viewModel()
) {
    val context = LocalContext.current
    val hobby by viewModel.hobby.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState()

    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("token", "") ?: ""

    LaunchedEffect(Unit) {
        viewModel.fetchMyHobby(token)
    }

    errorMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item { ProfileSection(hobby = hobby ?: "취미 없음") }

        item { MypageContentSection(title = "전체 시청내역", message = "시청내역이 없어요.") }

        item { MypageContentSection(title = "관심 프로그램", message = "관심 프로그램이 없어요.") }
    }
}

@Composable
fun ProfileSection(hobby: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MypageProfileImage()

        Spacer(modifier = Modifier.width(16.dp))

        MypageProfileEmail(email = hobby)

        Spacer(modifier = Modifier.weight(1f))

        MypageProfileActionButtons()
    }

    Spacer(modifier = Modifier.height(20.dp))

    MypagePurchaseMessage("첫 결제 시 첫 달 100원!")
    MypagePurchaseButton(onPurchaseClick = { /* 구매하기 동작 */ }, buttonText = "구매하기>")

    MypagePurchaseMessage("현재 보유하신 이용권이 없습니다.")
    MypagePurchaseButton(onPurchaseClick = { /* 구매하기 동작 */ }, buttonText = "구매하기>")
}


@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    MypageScreen()
}