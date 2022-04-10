package com.mestory.test.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mestory.test.domain.enums.Queries

@Entity
data class GoogleSearchOrganicResultEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Long,

    @ColumnInfo(name = "category")
    var category: Queries?,

    @SerializedName("link")
    @Expose
    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "date")
    var date: String,
)