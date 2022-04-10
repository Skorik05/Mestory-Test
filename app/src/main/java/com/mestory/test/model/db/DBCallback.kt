package com.mestory.test.model.db

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

class DBCallback: RoomDatabase.Callback() {
    companion object {
        private val TAG : String? = DBCallback::class.simpleName
    }
    override fun onCreate(db: SupportSQLiteDatabase) {
        Executors.newSingleThreadScheduledExecutor().execute { Log.d(TAG, "Database was created") }
    }
}