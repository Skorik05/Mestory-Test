package com.mestory.test.model.api

class ApiHelper(private val apiService : ApiService) {
    suspend fun searchInGoogle(q: String) = apiService.searchInGoogle(q)
    suspend fun openLink(url: String) = apiService.openLink(url)
}