package com.example.homework13

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CourseActivity : AppCompatActivity(R.layout.activity_course) {
    lateinit var coursNameTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coursNameTextView = findViewById(R.id.courseNameTextView)

    }

    //https://go.skillbox.ru/course/profession-android-developer/

    private fun handleIntentData() {
        intent.data?.lastPathSegment?.let { courseName ->
            coursNameTextView.text = courseName
        }
    }
}