package com.example.touchmeactivity.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.touchmeactivity.data.api.SupabaseAuthApi

object RetrofitInstance {
    private const val BASE_URL = "https://lvmybcyhrbisfjouhbrx.supabase.co/"

    val api: SupabaseAuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseAuthApi::class.java)
    }
}



