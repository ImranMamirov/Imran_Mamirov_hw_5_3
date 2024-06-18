package com.example.imran_mamirov_hw_5_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.imran_mamirov_hw_5_3.databinding.ItemImgBinding
import com.example.imran_mamirov_hw_5_3.models.Character

class CartoonPagingAdapter :
    PagingDataAdapter<Character, CharacterViewHolder>(CharacterItemCallback()) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class CharacterItemCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}

class CharacterViewHolder(private val binding: ItemImgBinding) : ViewHolder(binding.root) {
    fun bind(patch: Character) {
        binding.image.load(patch.image)
    }
}