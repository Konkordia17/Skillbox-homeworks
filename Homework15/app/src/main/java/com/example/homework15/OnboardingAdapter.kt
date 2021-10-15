package com.example.homework15

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(private val screens: List<OnboardingScreen>, activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen: OnboardingScreen = screens[position]
        return OnboardingFragment.newInstance(
            textRes = screen.textRes,
            screen.bgColorRes,
            screen.drawableRes
        )
    }
}