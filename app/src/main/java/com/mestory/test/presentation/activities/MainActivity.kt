package com.mestory.test.presentation.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mestory.test.databinding.ActivityMainBinding
import com.mestory.test.domain.enums.Queries
import com.mestory.test.domain.adapters.ResultAdapter
import com.mestory.test.domain.receivers.AlarmReceiver
import com.mestory.test.model.entities.GoogleSearchOrganicResultEntity
import com.mestory.test.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences

    private val resultsList: ArrayList<GoogleSearchOrganicResultEntity> = ArrayList<GoogleSearchOrganicResultEntity>()
    private lateinit var resultAdapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        resultAdapter = ResultAdapter(resultsList)
        binding.rvSearchHistory.layoutManager = LinearLayoutManager(this)
        binding.rvSearchHistory.adapter = resultAdapter

        viewModel.resultsHistory.observe(this, ::updateResultHistory)
        viewModel.loadResultsFromDB()

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            0,
            AlarmManager.INTERVAL_HOUR,
            pendingIntent
        )
    }

    private fun updateResultHistory(newResultsList: List<GoogleSearchOrganicResultEntity>) {
        Log.d("qdqwd", newResultsList.toString())
        var carResults = 0
        var airResult = 0
        var keyResult = 0
        newResultsList.forEach{
            when(it.category) {
                Queries.CAR -> carResults++
                Queries.AIR -> airResult++
                Queries.KEY -> keyResult++
                else -> {}
            }
        }
        resultsList.clear()
        resultsList.addAll(newResultsList)
        resultAdapter.notifyDataSetChanged()
        binding.tvWordCounter1.text = carResults.toString()
        binding.tvWordCounter2.text = airResult.toString()
        binding.tvWordCounter3.text = keyResult.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resultsHistory.removeObservers(this)
    }

    companion object {
        private const val ALARM_REQUEST_CODE = 1000

    }
}