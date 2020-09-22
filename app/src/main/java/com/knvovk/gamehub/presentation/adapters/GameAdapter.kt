package com.knvovk.gamehub.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.knvovk.gamehub.R
import com.knvovk.gamehub.databinding.CardGameBinding
import com.knvovk.gamehub.databinding.NetworkStateBinding
import com.knvovk.gamehub.domain.game.Game
import com.knvovk.gamehub.presentation.NetworkState
import com.knvovk.gamehub.presentation.extensions.showIf
import java.time.format.DateTimeFormatter
import java.util.*

class GameAdapter(
    private val retryCallback: () -> Unit
) : PagedListAdapter<Game, RecyclerView.ViewHolder>(diffCallback) {

    private var state: NetworkState? = null

    companion object {
        private const val UNKNOWN_VIEW_TYPE = "Unknown View Type"
        private val dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US)
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
        val gameBinding = CardGameBinding.inflate(inflater, parent, false)
        val networkBinding = NetworkStateBinding.inflate(inflater, parent, false)
        return when (viewType) {
            R.layout.card_game -> GameViewHolder(gameBinding)
            R.layout.network_state -> NetworkStateViewHolder(
                networkBinding,
                retryCallback
            )
            else -> throw IllegalArgumentException(UNKNOWN_VIEW_TYPE)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItemViewType(position)) {
            R.layout.card_game -> (holder as GameViewHolder).bind(getItem(position))
            R.layout.network_state -> (holder as NetworkStateViewHolder).bind(state)
            else -> throw IllegalArgumentException(UNKNOWN_VIEW_TYPE)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state
        } else {
            R.layout.card_game
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
        private val binding: CardGameBinding
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
                        in 75..100 -> metaColor = R.color.colorGreen
                        in 50..74 -> metaColor = R.color.colorYellow
                        in 0..49 -> metaColor = R.color.colorRed
                    }
                    chipMetacritic.setChipStrokeColorResource(metaColor)
                    chipMetacritic.setTextColor(ContextCompat.getColor(root.context, metaColor))
                    chipMetacritic.text = game.metacritic.toString()
                    textReleaseDate.text = game.released.format(dateFormat)
                    textReleaseDelimiter.showIf(game.genres.isNotEmpty())
                    textGenres.text = game.genres.joinToString { it._name }
                    textPlatforms.text = game.platforms.joinToString { it._name }
                }
            }
        }
    }

    class NetworkStateViewHolder(
        private val binding: NetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener { retryCallback() }
        }

        fun bind(state: NetworkState?) {
            with(binding) {
                textError.showIf(state === NetworkState.Failure)
                buttonRetry.showIf(state === NetworkState.Failure)
                progressBar.showIf(state === NetworkState.Loading)
            }
        }
    }
}