package com.knvovk.gamehub.presentation.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knvovk.gamehub.databinding.FragmentGameDetailsBinding
import com.knvovk.gamehub.presentation.extensions.hide
import com.knvovk.gamehub.presentation.extensions.show
import com.knvovk.gamehub.presentation.views.activities.MainActivity

class GameDetailsFragment : Fragment() {

    private var _binding: FragmentGameDetailsBinding? = null
    private lateinit var binding: FragmentGameDetailsBinding
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = (activity as MainActivity).also {
            it.binding.appBarLayout.hide()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        binding = _binding!!
        with(binding.toolbar) {
            setTitle(com.knvovk.gamehub.R.string.tools_title_toolbar_game_details)
            mainActivity.setSupportActionBar(this)
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity.binding.appBarLayout.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}