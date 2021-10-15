package com.example.homework15

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

class OnboardingFragment:Fragment(R.layout.fragment_onboarding) {
    lateinit var textView: TextView
    lateinit var imageView: ImageView
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView = requireView().findViewById(R.id.textView)
        imageView = requireView().findViewById(R.id.imageView)
        requireView().setBackgroundResource(requireArguments().getInt(KEY_COLOR))
        textView.setText(requireArguments().getInt(KEY_TEXT))
        imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))


    }


    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_COLOR = "color"
        private const val KEY_IMAGE = "image"


        fun newInstance(@StringRes textRes: Int,
                        @ColorRes bgColorRes: Int,
                        @DrawableRes drawableRes: Int): OnboardingFragment{
            return OnboardingFragment().withArguments {
                putInt(KEY_TEXT,textRes)
                putInt(KEY_COLOR, bgColorRes)
                putInt(KEY_IMAGE, drawableRes)
            }

        }
    }
}