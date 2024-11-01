package org.sopt.and.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.common.BannerView
import org.sopt.and.common.ListSection
import org.sopt.and.common.TopBar

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
        }

        item {
            TopBar(categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈"))
        }

        item {
            BannerView(imageResIds = listOf(R.drawable.queenbee, R.drawable.queenbee, R.drawable.queenbee))
        }

        item {
            ListSection(
                title = "믿고 보는 웨이브 에디터 추천작",
                imageList = listOf(R.drawable.mudo, R.drawable.mudo, R.drawable.mudo, R.drawable.mudo)
            )
        }

        item {
            ListSection(
                title = "오늘의 TOP 20",
                imageList = listOf(R.drawable.mudo, R.drawable.mudo, R.drawable.mudo, R.drawable.mudo)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(paddingValues = PaddingValues(0.dp))
}
