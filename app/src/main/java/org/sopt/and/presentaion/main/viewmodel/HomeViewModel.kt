package org.sopt.and.presentaion.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.and.R

class HomeViewModel : ViewModel() {
    private val _bannerImages = MutableLiveData(listOf(R.drawable.queenbee, R.drawable.sweethome, R.drawable.hbo))
    val bannerImages: LiveData<List<Int>> get() = _bannerImages

    private val _editorRecommendations = MutableLiveData(listOf(R.drawable.mudo, R.drawable.mudo, R.drawable.mudo, R.drawable.mudo))
    val editorRecommendations: LiveData<List<Int>> get() = _editorRecommendations

    private val _top20List = MutableLiveData(listOf(R.drawable.mudo, R.drawable.mudo, R.drawable.mudo, R.drawable.mudo))
    val top20List: LiveData<List<Int>> get() = _top20List
}