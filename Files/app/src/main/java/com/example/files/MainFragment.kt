package com.example.files

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.files.databinding.FragmentLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.TimeUnit

class MainFragment : Fragment(R.layout.fragment_layout) {
    private lateinit var binding: FragmentLayoutBinding
    private val sharedPrefs by lazy {
        requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }
    var fileUrl = ""
    private var fileName = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            checkIfFileExists(fileUrl)
        }
    }

    private fun downloadFile() {
        viewVisible(true)
        lifecycleScope.launch {
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return@launch
            fileUrl = binding.editText.text.toString()
            fileName =
                "${TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())}_${File(fileUrl).name}"
            val folder = requireContext().getExternalFilesDir("MyFolder")
            val file = File(folder, fileName)
            try {
                file.outputStream().use { fileOutputStream ->
                    Networking.api.getFile(fileUrl)
                        .byteStream()
                        .use { inputStream ->
                            inputStream.copyTo(fileOutputStream)
                        }
                    toast("Файл успешно загружен")
                    putDataInSharedPref(fileUrl, fileName)
                }
            } catch (t: Throwable) {
                Log.d("asd", "${t.message}")
                toast("Отсутствует соединение с интернетом")
                file.delete()
            } finally {
                viewVisible(false)
            }
        }
    }

    private fun putDataInSharedPref(key: String, value: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.edit()
                .putString(key, value)
                .apply()
        }
    }

    private fun checkIfFileExists(url: String) {
        if (sharedPrefs.contains(url)) {
            toast("Такой файл уже существует")
        } else {
            downloadFile()
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun viewVisible(isVisibility: Boolean) {
        if (isVisibility) {
            binding.progressBar.visibility = View.GONE
            binding.editText.isEnabled = true
            binding.button.isEnabled = true
        } else {
            binding.progressBar.visibility = View.VISIBLE
            binding.editText.isEnabled = false
            binding.button.isEnabled = false
        }
    }

    companion object {
        const val SHARED_NAME = "Files"
    }
}

