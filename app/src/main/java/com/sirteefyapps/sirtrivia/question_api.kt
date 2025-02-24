package com.sirteefyapps.sirtrivia

import com.sirteefyapps.sirtrivia.models.Questions
import retrofit2.http.GET

interface QuestionApi {
    @GET("world.json")
    suspend fun getWorldQuestions(): Questions
}