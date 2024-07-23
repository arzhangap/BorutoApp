package com.arzhang.borutoapp.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.arzhang.borutoapp.domain.repository.DataStoreOperations
import com.arzhang.borutoapp.util.Constants.PREFERENCES_KEY
import com.arzhang.borutoapp.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

// for accessing our preferences dataStore using name
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        // we are telling to preferences with which key our boolean value should be stored
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        // to persist value
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
      return dataStore.data
          .catch { exception ->
              if(exception is IOException ) {
                  emit(emptyPreferences())
              } else {
                  throw exception
              }
          }
          .map { preferences ->
              val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
              onBoardingState
          }
    }
}