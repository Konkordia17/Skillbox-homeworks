package com.example.loginfragment

import android.content.Context
import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.activity_fragment), Navigator {
    override fun onAttach(context: Context) {
        super.onAttach(context)
       navigateTo(ListFragment())
    }

    override fun navigateTo(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.containerMainFragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}
