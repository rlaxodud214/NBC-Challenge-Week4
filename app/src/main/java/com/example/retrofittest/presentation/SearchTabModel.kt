package com.example.retrofittest.presentation.ui

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

data class SearchTabModel(
    val fragment: Fragment,
    val title: String, // 분석 필요
    @DrawableRes val icon: Int, // 이건 알겠음
)
