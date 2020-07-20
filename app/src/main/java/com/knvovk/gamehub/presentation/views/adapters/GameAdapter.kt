package com.knvovk.gamehub.presentation.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knvovk.gamehub.databinding.ItemRecyclerGameBinding
import com.knvovk.gamehub.domain.models.gamemin.GameMin
import com.knvovk.gamehub.presentation.DATE_FORMAT

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    var data = mutableListOf<GameMin>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemRecyclerGameBinding.inflate(layoutInflater, parent, false)
        return GameViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(game = data[position])
    }

    class GameViewHolder(private val itemBinding: ItemRecyclerGameBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(game: GameMin) = with(itemBinding) {
            textItemGameName.text = game.name
            textItemGameRelease.text = game.released.format(DATE_FORMAT)
            textItemGameGenres.text = game.genres.joinToString { it._name }
            textItemGamePlatforms.text = game.platforms.joinToString { it._name }
        }
    }
}