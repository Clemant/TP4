package com.capou.tp4.remote

import com.capou.tp4.model.AddressRetrofit
import retrofit2.http.GET

interface AddressEndpoint {
    @GET("random_address")
    suspend fun getRandomQuote() : AddressRetrofit

}