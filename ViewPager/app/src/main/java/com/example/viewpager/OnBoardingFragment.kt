package com.example.viewpager

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment


@SuppressLint("ParcelCreator")
class OnboardingFragment() : Fragment(R.layout.fragment_onboarding), Parcelable {
    lateinit var textView: TextView
    lateinit var imageView: ImageView
    lateinit var eventButton: Button
    lateinit var tags: List<ArticleTag>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = requireView().findViewById(R.id.textView)
        imageView = requireView().findViewById(R.id.imageView)
        eventButton = requireView().findViewById(R.id.eventButton)
        textView.setText(requireArguments().getInt(KEY_TEXT))
        imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))

        eventButton.setOnClickListener {
            (parentFragment as? ViewPagerFragment)?.toGenerateEvent()
        }

    }

    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"
        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes drawableRes: Int
        ): OnboardingFragment {
            return OnboardingFragment().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_IMAGE, drawableRes)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }
}