package com.sirteefyapps.sirtrivia.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirteefyapps.sirtrivia.DataOrException
import com.sirteefyapps.sirtrivia.QuestionRepo
import com.sirteefyapps.sirtrivia.models.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel  @Inject constructor(private  val repository: QuestionRepo) : ViewModel() {
    private val _worldQuestions = MutableLiveData<DataOrException<Questions, Boolean, Exception>>()
    val questions: LiveData<DataOrException<Questions, Boolean, Exception>> = _worldQuestions
init {
    getWorldQuestions()
}
    private fun getWorldQuestions(){
        viewModelScope.launch {
            _worldQuestions.value = repository.getWorldQuestions()
        }
    }
}