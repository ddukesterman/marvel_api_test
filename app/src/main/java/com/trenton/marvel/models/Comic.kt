package com.trenton.marvel.models

import com.google.gson.annotations.SerializedName

data class Comic(
    val id: Int,
    val title: String,
    val description: String?,
    @SerializedName("textObjects")
    val comicTexts: List<ComicText>,
    val thumbnail: Thumbnail,
    val images: List<Image>,
    val creators: CreatorList
)
