package com.example.contentprovider.custm_content_provider

import android.content.*
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.example.contentprovider.BuildConfig
import com.squareup.moshi.Moshi

class MyContentProvider : ContentProvider() {
    private lateinit var userPrefs: SharedPreferences
    private lateinit var coursesPrefs: SharedPreferences
    private val userAdapter = Moshi.Builder().build().adapter(User::class.java)
    private val courseAdapter = Moshi.Builder().build().adapter(Course::class.java)
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITIES, PATH_USERS, TYPE_USERS)
        addURI(AUTHORITIES, PATH_COURSES, TYPE_COURSES)
        addURI(AUTHORITIES, "$PATH_USERS/#", TYPE_USER_ID)
        addURI(AUTHORITIES, "$PATH_COURSES/#", TYPE_COURSE_ID)
    }

    override fun onCreate(): Boolean {
        userPrefs = context!!.getSharedPreferences("user_shared_prefs", Context.MODE_PRIVATE)
        coursesPrefs = context!!.getSharedPreferences("course_shared_prefs", Context.MODE_PRIVATE)
        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        return when (uriMatcher.match(p0)) {
            TYPE_USERS -> {
                getAllUsersCursor()
            }
            else -> null
        }
    }

    private fun getAllUsersCursor(): Cursor {
        val allUsers = userPrefs.all.mapNotNull {
            val userJsonString = it.value as String
            userAdapter.fromJson(userJsonString)
        }
        val cursor = MatrixCursor(arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_AGE))
        allUsers.forEach {
            cursor.newRow()
                .add(it.id)
                .add(it.name)
                .add(it.age)
        }
        return cursor
    }

    override fun getType(p0: Uri): String? {
        return null
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        p1 ?: return null
        return when (uriMatcher.match(p0)) {
            TYPE_USERS -> {
                saveUser(p1)
            }
            else -> null
        }
    }

    private fun saveUser(contentValues: ContentValues): Uri? {
        val id = contentValues.getAsLong(COLUMN_USER_ID) ?: return null
        val name = contentValues.getAsString(COLUMN_USER_NAME) ?: return null
        val age = contentValues.getAsInteger(COLUMN_USER_AGE) ?: return null
        val user = User(id, name, age)
        userPrefs.edit()
            .putString(id.toString(), userAdapter.toJson(user))
            .apply()
        return Uri.parse("content://$AUTHORITIES/$PATH_USERS/$id")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return when (uriMatcher.match(p0)) {
            TYPE_USER_ID -> deleteUser(p0)
            else -> 0
        }
    }

    private fun deleteUser(uri: Uri): Int {
        val userId = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
        return if (userPrefs.contains(userId)) {
            userPrefs.edit()
                .remove(userId)
                .apply()
            1
        } else {
            0
        }
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        p1 ?: return 0
        return when (uriMatcher.match(p0)) {
            TYPE_USER_ID -> {
                updateUser(p0, p1)
            }
            else -> 0
        }
    }

    private fun updateUser(uri: Uri, contentValues: ContentValues): Int {
        val userId = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
        return if (userPrefs.contains(userId)) {
            saveUser(contentValues)
            1
        } else {
            0
        }
    }

    companion object {
        private const val AUTHORITIES = "${BuildConfig.APPLICATION_ID}.provider"
        private const val PATH_USERS = "users"
        private const val PATH_COURSES = "courses"
        private const val TYPE_USERS = 1
        private const val TYPE_COURSES = 2
        private const val TYPE_USER_ID = 3
        private const val TYPE_COURSE_ID = 4
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_NAME = "name"
        private const val COLUMN_USER_AGE = "age"
    }
}