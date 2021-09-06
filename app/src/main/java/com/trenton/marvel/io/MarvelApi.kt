package com.trenton.marvel.io

import com.trenton.marvel.models.ComicDataContainer
import com.trenton.marvel.models.ComicDataWrapper
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/comics")
    fun getComics() : Single<ComicDataContainer>

    @GET("/v1/public/comics/{id}")
    fun getComicWithId(@Path("id") id: Int) : Single<ComicDataWrapper>

}