package com.example.homework19

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PersonListViewModel : ViewModel() {
    private var persons = generatePerson(1000)

    private fun generatePerson(count: Int): List<Person> {
        val avatars = listOf(
            "https://cdn.pixabay.com/photo/2021/09/15/11/34/woman-6626615_960_720.jpg",
            "https://media.istockphoto.com/photos/confident-man-in-blue-sweater-portrait-picture-id536988396"
        )
        val names = listOf(
            "Иван Петров",
            "Сергей Пупкин",
            "Мария Сидорова"
        )
        val programmingLanguages = listOf(
            "Kotlin",
            "Java",
            "C++"
        )

        return (0..count).map {
            val id = it.toLong()
            val name = names.random()
            val avatar = avatars.random()
            val programmingLanguage = programmingLanguages.random()
            val isDeveloper = Random.nextBoolean()
            val age = Random.nextInt(15, 50)

            if (isDeveloper) {
                Person.Developer(
                    id = id,
                    name = name,
                    avatarLink = avatar,
                    age = age,
                    programmingLanguage = programmingLanguage
                )
            } else {
                Person.User(
                    id = id,
                    name = name,
                    avatarLink = avatar,
                    age = age
                )
            }
        }
    }

    fun addPerson() {
        val newUser = persons.random().let {
            when (it) {
                is Person.Developer -> it.copy(id = Random.nextLong())
                is Person.User -> it.copy(id = Random.nextLong())
            }
        }
        persons = listOf(newUser) + persons

    }

    fun deletePerson(position: Int) {
        persons = persons.filterIndexed { index, user -> index != position }
        }
    fun getPersonList() = persons

    }
