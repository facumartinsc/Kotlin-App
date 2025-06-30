package com.example.touchmeactivity.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.touchmeactivity.data.api.SupabaseAuthApi
import com.example.touchmeactivity.data.api.ScoreApi  // 👈 añadí esto

object RetrofitInstance {
    private const val BASE_URL = "https://lvmybcyhrbisfjouhbrx.supabase.co/rest/v1/"  // 👈 actualizamos para REST v1

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SupabaseAuthApi by lazy {
        retrofit.create(SupabaseAuthApi::class.java)
    }

    val scoreApi: ScoreApi by lazy {  // 👈 añadimos esto
        retrofit.create(ScoreApi::class.java)
    }
}
