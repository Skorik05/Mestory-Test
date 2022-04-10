package com.mestory.test.domain.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.mestory.test.domain.enums.Queries
import com.mestory.test.model.Repository
import com.mestory.test.model.api.ApiHelper
import com.mestory.test.model.api.RetrofitBuilder
import kotlinx.coroutines.*
import java.util.*

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val repo = Repository(ApiHelper(RetrofitBuilder.apiService))
        val parentJob = Job()
        CoroutineScope(Dispatchers.IO + parentJob).launch {
            try {
                val query = Queries.getRandom()
                Log.d("random1", query.toString())

                val response = repo.searchInGoogle(query.name)
                withContext(Dispatchers.Default) {
                    val body = response.body()
                    if (body != null) {
                        val result = body.organicResults[(body.organicResults.indices).random()]
                        repo.openLink(result.link)
                        result.category = query
                        result.date =  Calendar.getInstance().time.toString()
                        repo.saveResultToDB(result)
                        Log.d("saved", result.toString())
                    }
                }
            } catch (e : Exception){
                Log.d("SearchError", e.message.toString())
            }
        }
    }
}