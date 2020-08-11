package com.knvovk.gamehub.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.knvovk.gamehub.data.api.services.GameService
import com.knvovk.gamehub.data.mappers.GamesPageMapper
import com.knvovk.gamehub.domain.models.gamemin.Game
import com.knvovk.gamehub.presentation.NetworkState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate

class GameDataSource(
    private val service: GameService,
    private val mapper: GamesPageMapper,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, Game>() {

    private val _networkState = MutableLiveData<NetworkState>()
    private val _initialLoadState = MutableLiveData<NetworkState>()
    private var retry: Completable? = null

    val networkState: LiveData<NetworkState>
        get() = _networkState

    val initialLoadState: LiveData<NetworkState>
        get() = _initialLoadState

    companion object {
        private const val TAG = "GameMinDataSource"
        private val year = LocalDate.now().year
        private val date = "$year-01-01,$year-12-31"
        private const val page1 = 1
        private const val page2 = 2
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Game>
    ) {
        _initialLoadState.postValue(NetworkState.LOADING)
        _networkState.postValue(NetworkState.LOADING)
        service.getGamesByDate(
            release = date,
            pageSize = params.requestedLoadSize,
            page = page1
        ).map { mapper.fromNet(it).games }
            .subscribeBy(onNext = {
                retry = null
                _initialLoadState.postValue(NetworkState.SUCCESS)
                _networkState.postValue(NetworkState.SUCCESS)
                callback.onResult(it, null, page2)
            }, onError = {
                retry = Completable.fromAction { loadInitial(params, callback) }
                _initialLoadState.postValue(NetworkState.FAILURE)
                _networkState.postValue(NetworkState.FAILURE)
                Log.e(TAG, "loadInitial: ${it.message}", it)
            })
            .addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {
        _networkState.postValue(NetworkState.LOADING)
        service.getGamesByDate(
            release = date,
            pageSize = params.requestedLoadSize,
            page = params.key
        ).map { mapper.fromNet(it).games }
            .subscribeBy(onNext = {
                retry = null
                _networkState.postValue(NetworkState.SUCCESS)
                callback.onResult(it, params.key + 1)
            }, onError = {
                retry = Completable.fromAction { loadAfter(params, callback) }
                _networkState.postValue(NetworkState.FAILURE)
                Log.e(TAG, "loadAfter: ${it.message}", it)
            })
            .addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {}

    fun retry() {
        if (retry != null) {
            retry!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onComplete = {
                    // OK
                }, onError = {
                    Log.e(TAG, "retry: ${it.message}", it)
                })
                .addTo(disposables)
        }
    }

    class Factory(
        private val service: GameService,
        private val mapper: GamesPageMapper,
        private val disposables: CompositeDisposable
    ) : DataSource.Factory<Int, Game>() {

        val liveData = MutableLiveData<GameDataSource>()

        override fun create(): DataSource<Int, Game> {
            val source = GameDataSource(service, mapper, disposables)
            liveData.postValue(source)
            return source
        }
    }
}