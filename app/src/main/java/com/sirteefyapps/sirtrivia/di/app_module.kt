package com.sirteefyapps.sirtrivia.di

import com.sirteefyapps.sirtrivia.QuestionApi
import com.sirteefyapps.sirtrivia.QuestionRepo
import com.sirteefyapps.sirtrivia.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
     @Provides
     @Singleton
     fun provideQuestionApi(): QuestionApi {
         return Retrofit.Builder()
             .baseUrl(Constants.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(QuestionApi::class.java)
     }

    @Provides
    @Singleton
    fun provideQuestionRepo(questionApi: QuestionApi): QuestionRepo {
        return QuestionRepo(questionApi)
    }
}