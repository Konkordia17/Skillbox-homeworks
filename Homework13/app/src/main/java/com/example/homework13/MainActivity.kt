package com.example.homework13

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {


    lateinit var startExplicitButton: Button
    lateinit var messageEditText: EditText
    lateinit var sendEmailButton: Button
    lateinit var emailAdressEditText: EditText
    lateinit var emailSubject: EditText
    lateinit var takePhotoButton: Button
    lateinit var imageView: ImageView

    val imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cameraLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                it?.let { imageView.setImageBitmap(it) }
            }

        val photoPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) cameraLauncher.launch(null)
            }
        Log.d("LifecycleTest", "MainActivity|onCreate|${hashCode()}")
        startExplicitButton = findViewById(R.id.startExplicitButton)
        messageEditText = findViewById(R.id.messageEditText)
        sendEmailButton = findViewById(R.id.sendEmailButton)
        emailAdressEditText = findViewById(R.id.emailAddressEditText)
        emailSubject = findViewById(R.id.subjectEdittext)
        takePhotoButton = findViewById(R.id.takePhotoButton)
        imageView = findViewById(R.id.resultPhotoImageView)

        startExplicitButton.setOnClickListener {
            val messageText = messageEditText.text.toString()
            startActivity(SecondActivity.getIntent(this, messageText))
        }

        takePhotoButton.setOnClickListener {
            photoPermission.launch(android.Manifest.permission.CAMERA)
        }

        sendEmailButton.setOnClickListener {
            val emailAddress = emailAdressEditText.text.toString()
            val emailSubject = emailSubject.text.toString()
            val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
            if (!isEmailValid) {
                toast("Enter valid email address")
            } else {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
                    putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                }
                if (emailIntent.resolveActivity(packageManager) != null) {
                    startActivity(emailIntent)
                } else {
                    toast("No component to handle intent")
                }
            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

//    private fun dispatchTakePictureIntent() {
//        val isCameraPermissionGranted = ContextCompat.checkSelfPermission(
//            this,
//            android.Manifest.permission.CAMERA
//        ) == PackageManager.PERMISSION_GRANTED
//        if (!isCameraPermissionGranted) {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 14)
//        } else {
//            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            cameraIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(cameraIntent, PHOTO_REQUEST_CODE)
//            }
//        }
//    }

    companion object {
        private const val PHOTO_REQUEST_CODE = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHOTO_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val previewBitmap = data?.getParcelableExtra("data") as? Bitmap
                imageView.setImageBitmap(previewBitmap)
            } else {
                toast("Photo capture was cancelled")
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d("LifecycleTest", "MainActivity|onStart|${hashCode()}")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("LifecycleTest", "MainActivity|onResume|${hashCode()}")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("LifecycleTest", "MainActivity|onPause|${hashCode()}")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("LifecycleTest", "MainActivity|onStop|${hashCode()}")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("LifecycleTest", "MainActivity|onDestroy|${hashCode()}")
//    }

}

