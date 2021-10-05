package com.example.homework10

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class DynamicActivity : AppCompatActivity() {
    lateinit var addButton: Button
    lateinit var textInput: EditText
    lateinit var idContainer: LinearLayout
    lateinit var container: LinearLayout
    lateinit var textView: TextView
    lateinit var deleteButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)

        addButton = findViewById(R.id.addButton)
        textInput = findViewById(R.id.textInput)
        idContainer =findViewById(R.id.idContainer)
        container = findViewById(R.id.container)
        textView = findViewById(R.id.textView)
        deleteButton = findViewById(R.id.deletButton)

        addButton.setOnClickListener {
            val textToAdd = textInput.text.toString()
            val view = layoutInflater.inflate(R.layout.item_text, container, false)
            view.apply { textView.text = textToAdd
            deleteButton.setOnClickListener{
                container.removeView(this)
            }
            }
            container.addView(view)

//            val textViewToAdd = TextView(this).apply {
//                text = textToAdd
//               layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//                ).apply {
//                    when (Random.nextInt(3)) {
//                        0 -> Gravity.CENTER
//                        1 -> Gravity.END
//                        else -> Gravity.START
//                    }
//                }
//
//            }
//            idContainer.addView(textViewToAdd)

        }

    }
}