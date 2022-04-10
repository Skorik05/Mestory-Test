package com.mestory.test.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mestory.test.model.db.dao.GoogleSearchOrganicResultDAO
import com.mestory.test.model.entities.GoogleSearchOrganicResultEntity

@Database(entities = [GoogleSearchOrganicResultEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun googleSearchOrganicResultDAO(): GoogleSearchOrganicResultDAO
}