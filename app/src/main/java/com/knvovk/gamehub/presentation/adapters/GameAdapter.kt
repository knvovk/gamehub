package com.knvovk.gamehub.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.knvovk.gamehub.R
import com.knvovk.gamehub.databinding.ItemNetworkStateBinding
import com.knvovk.gamehub.databinding.ItemRecyclerGameBinding
import com.knvovk.gamehub.domain.game.Game
import com.knvovk.gamehub.presentation.DATE_FORMAT
import com.knvovk.gamehub.presentation.NetworkState
import com.knvovk.gamehub.presentation.extensions.showIf

class GameAdapter(
    private val retryCallback: () -> Unit
) : PagedListAdapter<Game, RecyclerView.ViewHolder>(diffCallback) {

    private var state: NetworkState? = null

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val gameBinding = ItemRecyclerGameBinding.inflate(inflater, parent, false)
        val networkBinding = ItemNetworkStateBinding.inflate(inflater, parent, false)
        return when (viewType) {
            R.layout.item_recycler_game -> GameViewHolder(gameBinding)
            R.layout.item_network_state -> NetworkStateViewHolder(
                networkBinding,
                retryCallback
            )
            else -> throw IllegalArgumentException("Unknown View Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItemViewType(position)) {
            R.layout.item_recycler_game -> (holder as GameViewHolder).bind(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bind(state)
            else -> throw IllegalArgumentException("Unknown View Type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_recycler_game
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow(): Boolean {
        return state != null && state !== NetworkState.Success
    }

    fun setNetworkState(state: NetworkState) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val prevState = this.state
                val hadExtraRow = hasExtraRow()
                this.state = state
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && prevState != state) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    class GameViewHolder(
        private val binding: ItemRecyclerGameBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game?) {
            game?.let {
                with(binding) {
                    Glide.with(root)
                        .load(game.image)
                        .into(imageBackground)
                    textName.text = game.name
                    var metaColor = 0
                    when (game.metacritic) {
                        in 75..100 -> metaColor = R.color.colorPastelGreen
                        in 50..74 -> metaColor = R.color.colorParisDaisy
                        in 0..49 -> metaColor = R.color.colorCarnation
                    }
                    chipMetacritic.setChipStrokeColorResource(metaColor)
                    chipMetacritic.setTextColor(ContextCompat.getColor(root.context, metaColor))
                    chipMetacritic.text = game.metacritic.toString()
                    textRelease.text = game.released.format(DATE_FORMAT)
                    textReleaseDelimiter.showIf(game.genres.isNotEmpty())
                    textGenres.text = game.genres.joinToString { it._name }
                    textPlatforms.text = game.platforms.joinToString { it._name }
                }
            }
        }
    }

    class NetworkStateViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener { retryCallback() }
        }

        fun bind(state: NetworkState?) {
            with(binding) {
                textError.showIf(state === NetworkState.Failure)
                textErrorHelp.showIf(state === NetworkState.Failure)
                buttonRetry.showIf(state === NetworkState.Failure)
                progressBar.showIf(state === NetworkState.Loading)
            }
        }
    }
}