package com.example.loginfragment


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), Navigator {

    lateinit var buttonLog: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateTo(LoginFragment())

    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)?.childFragmentManager?.findFragmentById(R.id.containerMainFragment)
        if (fragment is DetailFragment) {
            supportFragmentManager.findFragmentById(R.id.container)?.childFragmentManager?.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}


