package com.knvovk.gamehub.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.knvovk.gamehub.data.api.GameServiceGenerator
import com.knvovk.gamehub.data.implementations.GameRepositoryImpl
import com.knvovk.gamehub.data.mappers.GamesPageMapper
import com.knvovk.gamehub.domain.models.gamemin.GameMin
import com.knvovk.gamehub.presentation.State
import com.knvovk.gamehub.presentation.extensions.default
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class GamesViewModel : ViewModel() {

    private val repo = GameRepositoryImpl(
        service = GameServiceGenerator.create(),
        mapper = GamesPageMapper()
    )
    private val disposables = CompositeDisposable()
    private val _state = MutableLiveData<State<List<GameMin>>>().default(State.Loading())

    val state: LiveData<State<List<GameMin>>>
        get() = _state

    fun onLoadInitial() {
        repo.fetchTrendingGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                _state.value = State.Success(data = it)
            }, onError = { error ->
                _state.value = error.message?.let { State.Failure(msg = it) }
                Log.e("GamesViewModel", "onInitLoad: ${error.message}", error)
            })
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}