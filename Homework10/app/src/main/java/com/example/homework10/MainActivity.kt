package com.example.homework10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    lateinit var nameInputEmail: EditText
    lateinit var nameInputPassword: EditText
    lateinit var checkBox: CheckBox
    lateinit var button: Button
    lateinit var imageView: ImageView
    lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInputEmail = findViewById(R.id.nameTnputEmail)
        nameInputPassword = findViewById(R.id.nameTnputPassword)
        checkBox = findViewById(R.id.Checkbox)
        button = findViewById(R.id.Button)
        imageView = findViewById(R.id.imageView)
        container = findViewById(R.id.container)

        val imageUrl =
            "https://img1.fonwall.ru/o/rn/saturn-planet-digital-universe-artist.jpeg?route=mid&h=750"
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        nameInputEmail.doOnTextChanged { _, _, _, _ -> clickButton() }
        nameInputPassword.doOnTextChanged { _, _, _, _ -> clickButton() }
        checkBox.setOnCheckedChangeListener { _, _ -> clickButton() }
        button.setOnClickListener { login() }
    }

    private fun login() {
        val progressBar = ProgressBar(this)
        progressBar.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setViewEnabled(false)
        container.addView(progressBar)
        Handler().postDelayed({
            container.removeView(progressBar)
            Toast.makeText(this, R.string.long_operation_complite, Toast.LENGTH_SHORT).show()
            setViewEnabled(true)
        }, 2000)
    }

    private fun clickButton() {
        button.isEnabled = nameInputEmail.toString().isNotEmpty() && nameInputPassword.toString()
            .isNotEmpty() && checkBox.isChecked
    }

    private fun setViewEnabled(isEnabled: Boolean) {
        button.isEnabled = isEnabled
        nameInputEmail.isEnabled = isEnabled
        nameInputPassword.isEnabled = isEnabled
        checkBox.isEnabled = isEnabled
    }
}






