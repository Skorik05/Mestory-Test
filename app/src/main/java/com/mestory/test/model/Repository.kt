package com.mestory.test.model

import android.util.Log
import com.mestory.test.model.api.ApiHelper
import com.mestory.test.model.entities.GoogleSearchOrganicResultEntity
import com.mestory.test.presentation.App

class Repository(private val apiHelper: ApiHelper) {
    suspend fun searchInGoogle(q : String) = apiHelper.searchInGoogle(q)
    suspend fun openLink(url : String) = apiHelper.openLink(url)

    fun getAllResultsFromDb() = App.getInstance()?.getDb()?.googleSearchOrganicResultDAO()?.getAll()
    fun saveResultToDB(googleSearchOrganicResultEntity: GoogleSearchOrganicResultEntity) {
        try {
            App.getInstance()?.getDb()?.googleSearchOrganicResultDAO()?.insertResult(googleSearchOrganicResultEntity)
        } catch (e : Exception) {
            Log.d("InsertToDBError", "Couldn't save new instances to DB")
        }
    }
}