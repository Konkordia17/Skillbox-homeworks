package com.example.homework15

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingScreen (
    @StringRes val textRes: Int,
    @ColorRes val bgColorRes: Int,
    @DrawableRes val drawableRes: Int)
