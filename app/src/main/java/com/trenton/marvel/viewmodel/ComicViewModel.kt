package com.trenton.marvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trenton.marvel.ComicService
import com.trenton.marvel.models.Comic
import com.trenton.marvel.models.ComicDataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val comicService: ComicService
) : ViewModel() {

    val observableComic: MutableLiveData<Comic> by lazy {
        MutableLiveData<Comic>()
    }

    fun getComicData(comicId: Int) {
        comicService.getComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ComicDataWrapper>() {
                override fun onSuccess(container: ComicDataWrapper) {
                    observableComic.value = container.data.results[0]
                    dispose()
                }

                override fun onError(e: Throwable) {
                    dispose()
                }

            })

    }

}