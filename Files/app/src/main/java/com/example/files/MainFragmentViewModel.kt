package com.example.files

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.TimeUnit

class MainFragmentViewModel : ViewModel() {
    val toastLiveData = MutableLiveData<String>()
    val isVisible = MutableLiveData<Boolean>()
    private val context: Context
        get() = App.appContext
    private val sharedPrefs by lazy {
        context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }

    private fun downloadFilesFromAssets() {
        viewModelScope.launch {
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return@launch
            val folder = context.getExternalFilesDir("FirstFolder")
            try {
                val fileUrlsFromAssets = context.resources.assets.open("url.txt")
                    .bufferedReader()
                    .use { it.readLines() }
                fileUrlsFromAssets.forEach {
                    val firstFileName = "${
                        TimeUnit.MILLISECONDS
                            .toSeconds(System.currentTimeMillis())
                    }_${File(it).name}"
                    val file = File(folder, firstFileName)
                    try {
                        file.outputStream().use { fileOutputStream ->
                            Networking.api.getFile(it)
                                .byteStream()
                                .use { inputStream -> inputStream.copyTo(fileOutputStream) }
                            putDataInSharedPref(it, firstFileName)
                        }
                    } catch (t: Throwable) {
                        file.delete()
                        toastLiveData.postValue("Ошибка соединения")
                    }
                }
            } catch (t: Throwable) {
                folder?.delete()
                toastLiveData.postValue("Ошибка соединения")
            }
        }
    }

    private fun putDataInSharedPref(key: String, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sharedPrefs.edit()
                .putString(key, value)
                .apply()
        }
    }

    fun checkIsLoaded() {
        val flag = sharedPrefs.getString(KEY, "5")
        if (flag == "5") {
            downloadFilesFromAssets()
            putDataInSharedPref(KEY, "app is loaded")
        }
    }

    fun checkIfFileExists(fileUrl: String) {
        if (sharedPrefs.contains(fileUrl)) {
            toastLiveData.postValue("Такой файл уже существует")
        } else {
            downloadFile(fileUrl)
        }
    }

    private fun downloadFile(fileUrl: String) {
        isVisible.postValue(true)
        viewModelScope.launch {
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return@launch
            val fileName = "${
                TimeUnit.MILLISECONDS
                    .toSeconds(System.currentTimeMillis())
            }_${File(fileUrl).name}"
            val folder = context.getExternalFilesDir("MyFolder")
            val file = File(folder, fileName)
            try {
                file.outputStream().use { fileOutputStream ->
                    Networking.api.getFile(fileUrl)
                        .byteStream()
                        .use { inputStream ->
                            inputStream.copyTo(fileOutputStream)
                        }
                    toastLiveData.postValue("Файл успешно загружен")
                    putDataInSharedPref(fileUrl, fileName)
                }
            } catch (t: Throwable) {
                Log.d("asd", "${t.message}")
                toastLiveData.postValue("Отсутствует соединение с интернетом")
                file.delete()
            } finally {
                isVisible.postValue(false)
            }
        }
    }

    companion object {
        const val SHARED_NAME = "Files"
        const val KEY = "flag"
    }
}