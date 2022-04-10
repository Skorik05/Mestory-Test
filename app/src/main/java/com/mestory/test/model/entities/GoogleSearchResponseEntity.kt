package com.mestory.test.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GoogleSearchResponseEntity(
    @SerializedName("organic_results")
    @Expose
    val organicResults: List<GoogleSearchOrganicResultEntity>,
)
