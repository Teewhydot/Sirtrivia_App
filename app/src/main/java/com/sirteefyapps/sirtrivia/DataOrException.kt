package com.sirteefyapps.sirtrivia

data class DataOrException<T,Boolean, E: Exception>(
    var data: T? = null,
    var isLoading: Boolean,
    var exception: E? = null
)
