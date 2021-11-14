package com.example.myapplication3.famousQuote.remote

import com.example.myapplication3.chuckNorris.model.QuotableRetrofit
import retrofit2.http.GET

interface QuotableEndpoint {
    @GET("random")
    suspend fun getRandomQuote(): QuotableRetrofit
}