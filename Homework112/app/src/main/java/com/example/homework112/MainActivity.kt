package com.example.homework112

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val users= listOf("User1", "User2","Unknown")
    private lateinit var toolbar: Toolbar
    lateinit var expandTextView: TextView
    lateinit var searchResult:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        expandTextView = findViewById(R.id.expandTextView)
        searchResult = findViewById(R.id.searchResultTextView)
        initToolBar()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initToolBar(){
        toolbar.setNavigationOnClickListener {
            toast("Navigation clicked")
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_1 -> {
                    toast("action 1 clicked")
                    true
                }
                R.id.action_2 -> {
                    toast("action 2 clicked")
                    true
                }
                else -> false
            }
        }
       val searchItem = toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                expandTextView.text = "search expanded"
                return true

            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                expandTextView.text = "search collapsed"
                return true

            }
        })
        (searchItem.actionView as androidx.appcompat.widget.SearchView).
        setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter { it.contains(newText?:"", true) }
                    .joinToString()
                    .let {
                        searchResult.text = it
                    }
                return true
            }

        })

    }
}