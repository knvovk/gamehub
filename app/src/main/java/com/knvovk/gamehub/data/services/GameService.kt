package com.knvovk.gamehub.data.services

import com.knvovk.gamehub.domain.gamespage.GamesPageResponse
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("games")
    fun getNewTrendingGames(
        @Query("dates") release: String,
        @Query("ordering") ordering: String = "-rating",
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Observable<GamesPageResponse>

    companion object {
        private const val BASE_URL = "https://api.rawg.io/api/"
        private const val USER_AGENT = "GameHub Mobile App"

        private val headerInterceptor = Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header("User-Agent", USER_AGENT)
                .build()
            chain.proceed(request)
        }

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
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
}