package com.knvovk.gamehub.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knvovk.gamehub.databinding.FragmentGamesBinding
import com.knvovk.gamehub.domain.models.gamemin.GameMin
import com.knvovk.gamehub.presentation.State
import com.knvovk.gamehub.presentation.State.*
import com.knvovk.gamehub.presentation.extensions.toast
import com.knvovk.gamehub.presentation.viewmodels.GamesViewModel
import com.knvovk.gamehub.presentation.views.adapters.GameAdapter

class GamesFragment : Fragment() {

    private var binding: FragmentGamesBinding? = null
    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var recyclerGames: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private val viewModel by viewModels<GamesViewModel>()

    private val stateObserver = Observer<State<List<GameMin>>> { state ->
        when (state) {
            is Success -> showData(state.data!!)
            is Loading -> showLoading()
            is Failure -> showError(state.msg!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        progressBar = binding!!.progressBar
        gameAdapter = GameAdapter()
        recyclerGames = binding!!.recyclerGames.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gameAdapter
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, stateObserver)
        viewModel.onLoadInitial()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun showData(data: List<GameMin>) {
        progressBar.hide()
        gameAdapter.data = data
    }

    private fun showLoading() {
        progressBar.show()
    }

    private fun showError(msg: String) {
        toast(text = msg)
    }
}