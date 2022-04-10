package com.mestory.test.presentation

import android.app.Application
import androidx.room.Room
import com.mestory.test.model.db.AppDatabase
import com.mestory.test.model.db.DBCallback

class App: Application() {
    private var mDb: AppDatabase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        mDb = Room
            .databaseBuilder(this, AppDatabase::class.java, "app_db")
            .addCallback(DBCallback())
            .build()
    }


    fun getDb(): AppDatabase? {
        return mDb
    }

    companion object {
        private var instance: App? = null

        fun getInstance(): App? {
            return instance
        }
    }
}