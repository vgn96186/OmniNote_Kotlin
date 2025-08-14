package com.example.omninote.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val FLOATING_TOOLBAR = booleanPreferencesKey("floating_toolbar")
    }

    val isFloatingToolbar: Flow<Boolean> = context.dataStore.data
        .map {
            it[PreferencesKeys.FLOATING_TOOLBAR] ?: false
        }

    suspend fun setFloatingToolbar(isFloating: Boolean) {
        context.dataStore.edit {
            it[PreferencesKeys.FLOATING_TOOLBAR] = isFloating
        }
    }
}
