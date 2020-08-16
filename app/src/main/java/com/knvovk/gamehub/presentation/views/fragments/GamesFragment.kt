package com.knvovk.gamehub.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.knvovk.gamehub.databinding.FragmentGamesBinding
import com.knvovk.gamehub.domain.game.Game
import com.knvovk.gamehub.presentation.NetworkState
import com.knvovk.gamehub.presentation.adapters.GameAdapter
import com.knvovk.gamehub.presentation.extensions.showIf
import com.knvovk.gamehub.presentation.viewmodels.GamesViewModel

class GamesFragment : Fragment() {

    private var binding: FragmentGamesBinding? = null
    private lateinit var adapter: GameAdapter
    private val viewModel by viewModels<GamesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initRecyclerView() {
        adapter = GameAdapter(viewModel::retry)
        binding!!.recyclerGames.adapter = adapter
        viewModel.data.observe(
            viewLifecycleOwner,
            Observer<PagedList<Game>>(adapter::submitList)
        )
        viewModel.networkState.observe(
            viewLifecycleOwner,
            Observer<NetworkState>(adapter::setNetworkState)
        )
        viewModel.initialLoadState.observe(
            viewLifecycleOwner,
            Observer<NetworkState> { state ->
                if (adapter.currentList != null) {
                    if (adapter.currentList!!.isEmpty()) {
                        setInitialLoadState(state)
                    }
                } else {
                    setInitialLoadState(state)
                }
            }
        )
    }

    private fun setInitialLoadState(state: NetworkState?) {
        with(binding!!.networkState) {
            buttonRetry.setOnClickListener { viewModel.retry() }
            buttonRetry.showIf(state === NetworkState.Failure)
            textError.showIf(state === NetworkState.Failure)
            textErrorHelp.showIf(state === NetworkState.Failure)
            progressBar.showIf(state === NetworkState.Loading)
        }
    }
}