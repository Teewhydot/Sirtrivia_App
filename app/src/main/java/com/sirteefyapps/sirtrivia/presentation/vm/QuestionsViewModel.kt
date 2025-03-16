package com.sirteefyapps.sirtrivia.presentation.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirteefyapps.sirtrivia.DataOrException
import com.sirteefyapps.sirtrivia.QuestionRepo
import com.sirteefyapps.sirtrivia.models.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepo) : ViewModel() {

    private val _questionList: MutableState<DataOrException<Questions, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, false, Exception("No data"))
        )
    val questionList = _questionList
    var currentQuestionIndex = mutableIntStateOf(0)
    var currentScore = mutableIntStateOf(0)
        private set

    init {
        getWorldQuestions()
    }

    private fun getWorldQuestions() {
        viewModelScope.launch {
            _questionList.value = repository.getWorldQuestions()
        }
    }

    fun moveToNextQuestion() {
        currentQuestionIndex.intValue =
            (currentQuestionIndex.intValue + 1) % (_questionList.value.data?.size ?: 1)
    }

    fun resetQuiz() {
        currentQuestionIndex.intValue = 0
        currentScore.intValue = 0
    }
}
