package com.trenton.marvel.models

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ComicList
)
