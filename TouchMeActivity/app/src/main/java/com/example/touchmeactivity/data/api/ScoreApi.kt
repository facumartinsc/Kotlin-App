package com.example.touchmeactivity.data.api

import com.example.touchmeactivity.data.model.Score
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ScoreApi {

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imx2bXliY3locmJpc2Zqb3VoYnJ4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg1Mjk2NzcsImV4cCI6MjA2NDEwNTY3N30.f2t60RjJh91cNlggE_2ViwPXZ1eXP7zD18rWplSI4jE",
        "Content-Type: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6InBZbDlBZDlCckxWQmNLRkYiLCJ0eXAiOiJKV1QifQ..."
    )
    @POST("scores")
    suspend fun postScore(@Body score: Score)
}
