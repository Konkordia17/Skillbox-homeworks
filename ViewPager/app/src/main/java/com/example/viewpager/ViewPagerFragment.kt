package com.example.viewpager

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

class ViewPagerFragment : Fragment(R.layout.viewpager_fragment), Navigate {
    lateinit var viewPager: ViewPager2
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var buttonFilter: Button
    lateinit var springDotsIndicator: WormDotsIndicator

    private var currentTags = listOf(ArticleTag.POLITIC, ArticleTag.NEWS, ArticleTag.TECHNOLOGIES)

    private val screens: List<OnboardingScreen> = listOf(
        OnboardingScreen(
            ArticleTag.TECHNOLOGIES,
            textRes = R.string.page1,
            drawableRes = R.drawable.page1
        ),
        OnboardingScreen(
            ArticleTag.POLITIC,
            textRes = R.string.page2,
            drawableRes = R.drawable.page2
        ),
        OnboardingScreen(
            ArticleTag.NEWS,
            textRes = R.string.page3,
            drawableRes = R.drawable.page3
        ),
        OnboardingScreen(
            ArticleTag.TECHNOLOGIES,
            textRes = R.string.page4,
            drawableRes = R.drawable.page4
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = requireView().findViewById(R.id.viewPager)
        springDotsIndicator = requireView().findViewById(R.id.dots_indicator)
        tabLayout = requireView().findViewById(R.id.tabLayout)
        toolbar = requireView().findViewById(R.id.toolbar)
        buttonFilter = requireView().findViewById(R.id.filter)
        val adapter = OnboardingAdapter(screens, this)
        viewPager.adapter = adapter
        springDotsIndicator.setViewPager2(viewPager)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })
        viewPager.setPageTransformer { page, position ->
            when {
                position < -1 -> {
                    page.alpha = 0f
                }
                position <= 0 -> {
                    page.alpha = 1f
                    page.pivotX = page.width.toFloat()
                    page.rotationY = -90 * abs(position)
                }
                position <= 1 -> {
                    page.alpha = 1f;
                    page.pivotX = 0f;
                    page.rotationY = 90 * abs(position)
                }
                position > 1 -> {
                    page.alpha = 0f
                }
            }
            when {
                abs(position) <= 0.5 -> {
                    page.scaleY = max(0.4f, 1 - abs(position))
                }
                abs(position) <= 1 -> {
                    page.scaleY = max(0.4f, 1 - abs(position))
                }

            }
        }

        buttonFilter.setOnClickListener {
            showDialog()
        }
    }

    override fun navigateTo(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.containerViewPager, fragment)
            .commit()
    }

    private fun showDialog() {
        FilterDialog.newInstance(currentTags)
            .show(childFragmentManager, "dialog")
    }

    fun toGenerateEvent() {
        val tabNumber = Random.nextInt(0, screens.size)
        tabLayout.getTabAt(tabNumber)?.orCreateBadge?.apply {
            number += 1
            badgeGravity = BadgeDrawable.TOP_END
        }
    }

    fun okClicked(tags: List<ArticleTag>) {
        currentTags = tags
        val filteredScreens = screens.filter {
            tags.contains(it.tag)
        }
        val adapter = OnboardingAdapter(filteredScreens, this)
        viewPager.adapter = adapter
        springDotsIndicator.setViewPager2(viewPager)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    fun cancelClicked() {
    }
}
