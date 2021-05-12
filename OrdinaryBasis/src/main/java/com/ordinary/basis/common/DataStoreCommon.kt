package com.ordinary.basis.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ordinary.basis.AppContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 *
 * @Author: GQ
 * @Date: 2021/4/1 18:05
 */
object DataStoreCommon {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val gson by lazy { Gson() }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

    fun clearData() {
        coroutineScope.launch {
            AppContext.application.dataStore.edit {
                it.clear()
            }
        }
    }

    private suspend fun <T> putEntity(key: Preferences.Key<String>, t: T) {
        AppContext.application.dataStore.edit { dataStore ->
            dataStore[key] = gson.toJson(t)
        }
    }

    private suspend inline fun <reified T> getEntity(
        key: Preferences.Key<String>,
        crossinline callback: (T?) -> Unit
    ) {
        AppContext.application.dataStore.data.map { preferences: Preferences ->
            preferences[key]
        }.collect {
            val fromJson = gson.fromJson(it, T::class.java)
            withContext(Dispatchers.Main) {
                callback(fromJson)
            }
        }
    }

    private suspend inline fun <T> getEntityList(
        key: Preferences.Key<String>,
        crossinline callback: (MutableList<T>?) -> Unit
    ) {
        AppContext.application.dataStore.data.map { preferences: Preferences ->
            preferences[key]
        }.collect {
            val fromJson: MutableList<T>? =
                gson.fromJson(it, object : TypeToken<MutableList<T>>() {}.type)
            withContext(Dispatchers.Main) {
                callback(fromJson)
            }
        }
    }
}