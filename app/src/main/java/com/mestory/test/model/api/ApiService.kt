package com.mestory.test.model.api

import com.mestory.test.model.entities.GoogleSearchResponseEntity
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("https://serpapi.com/search.json/")
    suspend fun searchInGoogle(
        @Query(QUERY) q: String,
        @Query(GOOGLE_DOMAIN) googleDomain : String = "google.com",
        @Query(API_KEY) key : String = "5d2e76fb94abe407ece9ed8206787780672b1c0c8e4becb30c25dc9744864f36"

    ) : Response<GoogleSearchResponseEntity>

    @GET
    suspend fun openLink(@Url url: String) : Response<ResponseBody>

    companion object {
        private const val QUERY = "q"
        private const val GOOGLE_DOMAIN = "google_domain"
        private const val API_KEY = "api_key"
    }
}