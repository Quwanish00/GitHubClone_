package com.example.github.data.models

data class GenerateData<out T>(
    val total_count:Int,
    val incomplete_result:Boolean,
    val items:List<T>
)
