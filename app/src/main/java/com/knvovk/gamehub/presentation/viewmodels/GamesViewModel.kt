package com.knvovk.gamehub.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.knvovk.gamehub.data.services.GameService
import com.knvovk.gamehub.data.mappers.GamesPageMapper
import com.knvovk.gamehub.data.repositories.GameDataSource
import com.knvovk.gamehub.domain.game.Game
import com.knvovk.gamehub.presentation.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class GamesViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    private val factory: GameDataSource.Factory

    val data: LiveData<PagedList<Game>>
    val initialLoadState: LiveData<NetworkState>
    val networkState: LiveData<NetworkState>

    companion object {
        private const val pageSize = 15
    }

    init {
        factory = GameDataSource.Factory(
            service = GameService.create(),
            mapper = GamesPageMapper(),
            disposables = disposables
        )
        val config = Config(
            pageSize = pageSize,
            initialLoadSizeHint = pageSize
        )
        data = factory.toLiveData(config)
        initialLoadState = Transformations
            .switchMap<GameDataSource, NetworkState>(
                factory.liveData,
                GameDataSource::initialLoadState
            )
        networkState = Transformations
            .switchMap<GameDataSource, NetworkState>(
                factory.liveData,
                GameDataSource::networkState
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun retry() {
        factory.liveData.value!!.retry()
    }
}