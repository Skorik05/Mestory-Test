package com.mestory.test.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mestory.test.model.entities.GoogleSearchOrganicResultEntity
import io.reactivex.Single

@Dao
interface GoogleSearchOrganicResultDAO {
    @Insert
    fun insertResult(googleSearchOrganicResultEntity : GoogleSearchOrganicResultEntity)

    @Query("SELECT * FROM GoogleSearchOrganicResultEntity ORDER BY id")
    fun getAll(): List<GoogleSearchOrganicResultEntity>

}