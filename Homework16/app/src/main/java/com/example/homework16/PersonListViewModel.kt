package com.example.homework16

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PersonListViewModel : ViewModel() {
    private val repository = PersonRepository()

    private val personLiveData = MutableLiveData(repository.generatePerson(1000))

    private val showToastLiveData = MutableLiveData<Unit> ()

//    private var persons = repository.generatePerson(1000)

    val persons: LiveData<List<Person>>
        get() = personLiveData

    val showToast:LiveData<Unit>
    get() = showToastLiveData

    fun addPerson() {
        val newUser = repository.createPerson()
        val updateList = listOf(newUser) + personLiveData.value.orEmpty()
        personLiveData.postValue(updateList)
        showToastLiveData.postValue(Unit)
//        persons = listOf(newUser) + persons
    }

    fun deletePerson(position: Int) {
        personLiveData.postValue(repository.deletePerson(personLiveData.value.orEmpty(), position))
//        persons = repository.deletePerson(persons, position)
    }

}