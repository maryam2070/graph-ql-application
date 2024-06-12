package com.example.grapgqlapplication.data.model

data class Pokemon(
    val abilities: List<String>,
    val id: Int,
    val message: String,
    val moves: List<String>,
    val name: String,
    val status: Boolean,
    val types: List<Type>
)