package com.example.homework15

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabsActivity:AppCompatActivity(R.layout.activity_tabc) {
    lateinit var viewPager:ViewPager2
    lateinit var tabLayout: TabLayout
    private val screens: List<OnboardingScreen> = listOf(
        OnboardingScreen(
            textRes = R.string.onboarding_text_1,
            bgColorRes = R.color.onboarding_color_1,
            drawableRes = R.drawable.onboarding_drawable_1
        ),
        OnboardingScreen(
            textRes = R.string.onboarding_text_2,
            bgColorRes = R.color.onboarding_color_2,
            drawableRes = R.drawable.onboarding_drawable_2
        ),
        OnboardingScreen(
            textRes = R.string.onboarding_text_3,
            bgColorRes = R.color.onboarding_color_3,
            drawableRes = R.drawable.onboarding_drawable_3
        ),
        OnboardingScreen(
            textRes = R.string.onboarding_text_4,
            bgColorRes = R.color.onboarding_color_4,
            drawableRes = R.drawable.onboarding_drawable_4
        ),
        OnboardingScreen(
            textRes = R.string.onboarding_text_5,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.onboarding_drawable_5
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = OnboardingAdapter(screens, this)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tablayout)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.text = "Tab ${position+1}"
            if(position%2==0){
                tab.setIcon(R.drawable.ic_baseline_ac_unit_24)
            }
        }.attach()

        tabLayout.getTabAt(1)?.orCreateBadge ?.apply {
            number = 2
            badgeGravity = BadgeDrawable.TOP_END
        }
        viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })


    }
}