package com.example.converter.ui.download

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converter.data.Data
import com.example.converter.data.Networking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DownloadViewModel : ViewModel() {
    private val downloadLiveData = MutableLiveData<Boolean>()
    val liveData: LiveData<Boolean>
        get() = downloadLiveData

    fun downloadFile() {
        viewModelScope.launch {
            try {
                val currenciesList = withContext(Dispatchers.IO) {
                    Networking.valuteApi.getValutesList().execute().body()
                }
                val valutes = currenciesList?.valutes
                if (valutes != null) {
                    Data.valutes = valutes
                    isLoadingData()
                }
            } catch (t: Throwable) {
            }
        }
    }

    private fun isLoadingData() {
        if (Data.valutes.isNotEmpty()) {
            downloadLiveData.postValue(true)
        }
    }
}


