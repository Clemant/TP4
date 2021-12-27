package com.capou.tp4.architecture

import com.capou.tp4.remote.AddressEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://random-data-api.com/api/address/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getAddress(): AddressEndpoint = retrofit.create(AddressEndpoint::class.java)
}