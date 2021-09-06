package com.trenton.marvel

import android.content.Context
import com.trenton.marvel.io.MarvelApi
import com.trenton.marvel.io.Network
import com.trenton.marvel.models.ComicDataContainer
import com.trenton.marvel.models.ComicDataWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ComicService
@Inject
constructor(
    @ApplicationContext context: Context,
    network: Network
) {

    private val retrofit = network.createNetworkClient(context, "http://gateway.marvel.com")
    private var marvelApi : MarvelApi = retrofit.create(MarvelApi::class.java)

    fun getComic(id: Int): Single<ComicDataWrapper> {
        return marvelApi.getComicWithId(id)
    }
}