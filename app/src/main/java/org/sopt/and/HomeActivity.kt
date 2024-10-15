package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        // 최상단 배너뷰
        item {
            BannerView()
        }

        // 믿고 보는 웨이브 에디터 추천작
        item {
            ListSection(title = "믿고 보는 웨이브 에디터 추천작", list = listOf("item1", "item2", "item3", "item4"))
        }

        // 오늘의 TOP 20
        item {
            ListSection(title = "오늘의 TOP 20", list = listOf("1위", "2위", "3위", "4위"))
        }
    }
}

@Composable
fun BannerView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.DarkGray)
    ) {
        // Image 대신 Box로 임시 배경 설정
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray) // 임시 배경 색상
        ) {
            Text(
                text = "Image Placeholder",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // 텍스트 배너 설명
        Text(
            text = "여왕벌게임",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}


@Composable
fun ListSection(title: String, list: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 8.dp) // 리스트 왼쪽 여백 추가
        ) {
            items(list.size) { index ->
                ItemCard(list[index])
            }
        }
    }
}


@Composable
fun ItemCard(item: String) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 이미지 대신 Box로 임시 배경 설정
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.DarkGray, RoundedCornerShape(4.dp))
        ) {
            // 임시 이미지 대체 텍스트
            Text(
                text = "Image",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 아이템 텍스트
        Text(text = item, color = Color.White, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
