package com.example.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(private val screens: List<OnboardingScreen>, fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen: OnboardingScreen = screens[position]
        return OnboardingFragment.newInstance(
            screen.textRes, screen.drawableRes
        )
    }
}