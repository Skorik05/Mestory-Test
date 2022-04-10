package com.mestory.test.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mestory.test.model.Repository
import com.mestory.test.model.api.ApiHelper
import com.mestory.test.model.api.RetrofitBuilder
import com.mestory.test.model.entities.GoogleSearchOrganicResultEntity
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val mutableResultsHistory = MutableLiveData<List<GoogleSearchOrganicResultEntity>>()
    var resultsHistory : LiveData<List<GoogleSearchOrganicResultEntity>> = mutableResultsHistory

    private val repo = Repository(ApiHelper(RetrofitBuilder.apiService))
    private var job : Job? = null

    fun loadResultsFromDB() {
        job = CoroutineScope(Dispatchers.IO).launchPeriodicAsync(3000) {
            try {
                val results = repo.getAllResultsFromDb()
                if (results != null) {
                    mutableResultsHistory.postValue(results)
                }
            } catch (e : Exception){
                Log.d("LoadResultsFromDBError", e.message.toString())
            }
        }
    }

    fun CoroutineScope.launchPeriodicAsync(
        repeatMillis: Long,
        action: () -> Unit
    ) = this.async {
        if (repeatMillis > 0) {
            while (isActive) {
                action()
                delay(repeatMillis)
            }
        } else {
            action()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}