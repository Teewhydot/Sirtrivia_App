package com.sirteefyapps.sirtrivia.models

data class QuestionsItem(
    val answer: String = "",
    val category: String = "",
    val choices: List<String> = listOf(),
    val question: String = ""
)