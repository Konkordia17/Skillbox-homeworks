package com.example.myapplication_

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.random.Random

class HandlerFragment : Fragment(R.layout.fragment_threading) {
    private val mainHandler = Handler(Looper.getMainLooper())
    lateinit var executeAction: Button
    lateinit var textView: TextView
    private lateinit var handler: Handler
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        executeAction = requireView().findViewById(R.id.executeButton)
        textView = requireView().findViewById(R.id.textView)
        val backgroundThread = HandlerThread("handler thread").apply {
            start()
        }
        handler = Handler(backgroundThread.looper)
//        Thread {
//            Looper.prepare()
//            handler = Handler()
//
//            Looper.loop()
//
//        }.start()

        executeAction.setOnClickListener {
            handler.post {
                val randomNumber = Random.nextLong()
                mainHandler.post {
                    textView.text = randomNumber.toString()
                }
                mainHandler.postDelayed( {Toast.makeText(requireContext(),
                    "Generated number = $randomNumber",Toast.LENGTH_SHORT).show()},
                    500)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.looper.quit()
    }
}