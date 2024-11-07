package org.sopt.and.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MypageViewModel : ViewModel() {
    private val _email = MutableLiveData("user@example.com") // 타입 인수 제거
    val email: LiveData<String> get() = _email
}
