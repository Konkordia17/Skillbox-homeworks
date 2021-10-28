package com.example.homework19.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework19.data.InstrumentRepository
import com.example.homework19.model.MusicalInstruments
import com.example.homework19.utils.SingleLiveEvent

class InstrumentListViewModel : ViewModel() {
    private val repository = InstrumentRepository()
    private val instrumentLiveData =
        MutableLiveData(repository.musicalInstruments)

    private val showToastDelete = SingleLiveEvent<Unit>()
    val instruments: LiveData<List<MusicalInstruments>>
        get() = instrumentLiveData

    val showToast: LiveData<Unit>
        get() = showToastDelete

    fun addInstrument(instruments: MusicalInstruments) {
        val updatedList = listOf(instruments) + instrumentLiveData.value.orEmpty()
        instrumentLiveData.postValue(updatedList)
    }

    fun deleteInstrument(position: Int) {
        instrumentLiveData.postValue(
            repository.deleteInstrument(
                instrumentLiveData.value.orEmpty(), position
            )
        )
        showToastDelete.postValue(Unit)
    }
}