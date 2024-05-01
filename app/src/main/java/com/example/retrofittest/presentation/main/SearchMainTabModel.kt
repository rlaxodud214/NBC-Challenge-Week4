package com.example.retrofittest.presentation.main

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

data class SearchMainTabModel(
    val fragment: Fragment,
    val title: String, // 분석 필요
    @DrawableRes val icon: Int, // 이건 알겠음
)
