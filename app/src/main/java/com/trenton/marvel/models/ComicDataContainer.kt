package com.trenton.marvel.models

data class ComicDataContainer(
    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : List<Comic>,
)
