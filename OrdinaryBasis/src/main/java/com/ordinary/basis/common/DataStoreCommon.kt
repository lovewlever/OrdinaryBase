package com.ordinary.basis.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ordinary.basis.AppContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * @Author: GQ
 * @Date: 2021/4/1 18:05
 */
object DataStoreCommon {

    private val coroutineScope by lazy {
        CoroutineScope(Dispatchers.Main)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

    fun putData() {
        coroutineScope.launch {
            AppContext.application.dataStore.edit { mp: MutablePreferences ->
                mp[stringPreferencesKey("name")] = "1"
            }
        }
    }
}