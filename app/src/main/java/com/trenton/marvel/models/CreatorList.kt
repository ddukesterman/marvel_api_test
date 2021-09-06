package com.trenton.marvel.models

data class CreatorList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<CreatorSummary>
) {
}