package com.example.toolbarhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var textView: TextView
    lateinit var searchResult: TextView
    private val users = listOf("user1", "user2", "Unknown")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.Toolbar)
        initToolbar()
        textView = findViewById(R.id.expand_textView)
        searchResult = findViewById(R.id.searchResult)
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initToolbar() {
        toolbar.setOnClickListener {
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

        val searchItem = toolbar.menu.findItem(R.id.action_Search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                textView.text = "search expanded"
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                textView.text = "search collapsed"
                return true
            }
        })
        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter { it.contains(newText ?: "", true) }
                    .joinToString()
                    .let { searchResult.text = it }
                return true
            }
        })
    }
}