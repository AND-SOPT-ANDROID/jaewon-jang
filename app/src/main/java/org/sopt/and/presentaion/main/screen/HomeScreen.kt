package org.sopt.and.presentaion.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentaion.main.componet.HomeBannerView
import org.sopt.and.presentaion.main.componet.HomeListSection
import org.sopt.and.presentaion.main.componet.HomeTopBar
import org.sopt.and.presentaion.main.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val bannerImages = viewModel.bannerImages.observeAsState(emptyList())
    val editorRecommendations = viewModel.editorRecommendations.observeAsState(emptyList())
    val top20List = viewModel.top20List.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Wavve Logo",
                    modifier = Modifier
                        .height(24.dp)
                        .width(100.dp)
                )
        }

        item {
            HomeTopBar(categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈"))
        }

        item {
            HomeBannerView(imageResIds = bannerImages.value)
        }

        item {
            HomeListSection(
                title = "믿고 보는 웨이브 에디터 추천작",
                imageList = editorRecommendations.value
            )
        }

        item {
            HomeListSection(
                title = "오늘의 TOP 20",
                imageList = top20List.value
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(paddingValues = PaddingValues(0.dp))
}
