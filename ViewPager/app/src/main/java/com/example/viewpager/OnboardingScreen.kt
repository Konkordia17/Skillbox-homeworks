package com.example.viewpager

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingScreen(
    val tag: ArticleTag,
    @StringRes val textRes: Int,
    @DrawableRes val drawableRes: Int
)
