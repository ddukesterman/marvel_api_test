package com.trenton.marvel.io

import android.content.Context
import com.trenton.marvel.config.MarvelConfigService
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import java.math.BigInteger


class Network
@Inject
constructor(
    private val marvelConfigService: MarvelConfigService
) {
    fun createNetworkClient(context: Context, baseUrl: String) : Retrofit {
        val cacheNetworkInterceptor = CacheNetworkInterceptor(marvelConfigService)

        val client = OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            addInterceptor(cacheNetworkInterceptor)
            cache(Cache(File(context.cacheDir, "okhttp-cache"), CACHE_SIZE))

        }.build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(client)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()

    }

    companion object {
        private const val CACHE_SIZE: Long = 32 * 1024 * 1024 //32MB
    }

}

class CacheNetworkInterceptor
constructor(
    private val marvelConfigService: MarvelConfigService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis()

        var originalChain = chain.request()
        val originalUrl = originalChain.url()
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("apikey", marvelConfigService.publicKey)
            .addQueryParameter("hash", getHash(ts, marvelConfigService.publicKey, marvelConfigService.privateKey))
            .build()

        val requestBuilder = originalChain.newBuilder()
            .url(newUrl)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

    private fun getHash(ts: Long, publicKey: String, privateKey: String): String {
        val hash = "$ts$privateKey$publicKey"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(hash.toByteArray())).toString(16).padStart(32, '0')
    }

}