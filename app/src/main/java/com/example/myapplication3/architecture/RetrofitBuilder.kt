package com.example.myapplication3.architecture

import com.example.myapplication3.famousQuote.remote.QuotableEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private val retrofitQuotable: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.quotable.io")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()


    fun getQuotable(): QuotableEndpoint = retrofitQuotable.create(QuotableEndpoint::class.java)
}
