package com.trenton.marvel.config

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class MarvelConfigService
@Inject
constructor(
    @ApplicationContext context: Context
) {

    val publicKey = "059912624a9eacc16ba39d01484cc4f0"
    val privateKey = "67c762fb6735763190431187b9733e6f76744942"

}