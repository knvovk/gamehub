package com.knvovk.gamehub.presentation.views.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.knvovk.gamehub.R
import com.knvovk.gamehub.data.api.GamesPageServiceGenerator
import com.knvovk.gamehub.data.implementations.GamesPageRepositoryImpl
import com.knvovk.gamehub.data.mappers.GamesPageMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadGames()
    }

    private fun loadGames() {
        val repo = GamesPageRepositoryImpl(
            service = GamesPageServiceGenerator.create(),
            mapper = GamesPageMapper()
        )
        repo.fetchTrendingGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { page ->
                page.games.forEach {
                    Log.d(TAG, "loadGames: $it")
                }
            }, onError = { error ->
                Log.e(TAG, "loadGames: $error", error)
            })
    }
}