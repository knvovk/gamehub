package com.knvovk.gamehub.data.api

import com.knvovk.gamehub.data.api.services.USER_AGENT
import com.knvovk.gamehub.data.api.services.BASE_URL
import com.knvovk.gamehub.data.api.services.GameService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object GameServiceGenerator {

    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request()
        request = request.newBuilder()
            .header("User-Agent", USER_AGENT)
            .build()
        chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(httpClient)
        .build()

    fun create(): GameService = retrofit.create()
}