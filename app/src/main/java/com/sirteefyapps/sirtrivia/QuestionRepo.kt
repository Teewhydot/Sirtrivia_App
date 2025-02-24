package com.sirteefyapps.sirtrivia

import com.sirteefyapps.sirtrivia.models.Questions
import javax.inject.Inject


class QuestionRepo  @Inject constructor(private val questionApi: QuestionApi) {
    private val listOfQuestionsItems = DataOrException<Questions, Boolean, Exception>(isLoading = false)
    suspend fun getWorldQuestions(): DataOrException<Questions,Boolean,Exception> {
        try {
            listOfQuestionsItems.isLoading = true
            val response = questionApi.getWorldQuestions()
            listOfQuestionsItems.data = response
            if(listOfQuestionsItems.data?.isNotEmpty() == true){
                listOfQuestionsItems.isLoading = false
            }
        } catch (e: Exception) {
            listOfQuestionsItems.exception = e
        }
        listOfQuestionsItems.isLoading = false
        return listOfQuestionsItems
    }
}