package org.sopt.and.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            TopBar()
        }

        item {
            BannerView()
        }

        item {
            ListSection(
                title = "믿고 보는 웨이브 에디터 추천작",
                list = listOf(R.drawable.mudo, R.drawable.mudo, R.drawable.mudo, R.drawable.mudo)
            )
        }

        item {
            ListSection(
                title = "오늘의 TOP 20",
                list = listOf(R.drawable.mudo, R.drawable.mudo, R.drawable.mudo, R.drawable.mudo)
            )
        }
    }
}

@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Wavve Logo",
                modifier = Modifier
                    .height(24.dp)
                    .width(100.dp)
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈")) { category ->
                Text(
                    text = category,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun BannerView() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(5) { _ ->
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp)
                    .background(Color.DarkGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.queenbee),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ListSection(title: String, list: List<Int>) {
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
            items(list.size) { index ->
                ItemCard(imageResId = list[index])
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(paddingValues = PaddingValues(0.dp))
}
