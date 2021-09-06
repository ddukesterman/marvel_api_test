package com.trenton.marvel.models

data class CharacterDataContainer(
    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : List<Character>,
)
