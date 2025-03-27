package com.example.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wish.databinding.ItemWishBinding

class WishAdapter(
    private var wishes: MutableList<Wish>,
    private val onDeleteClick: (Wish) -> Unit,
    private val onGrantClick: (Wish) -> Unit
) : RecyclerView.Adapter<WishAdapter.WishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val binding = ItemWishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        holder.bind(wishes[position])
    }

    override fun getItemCount(): Int = wishes.size
    fun updateWishes(newWishes: MutableList<Wish>) {
        wishes.clear() // Clears old list
        wishes.addAll(newWishes) // Adds new data
        notifyDataSetChanged() // Notifies adapter
    }


    fun getWishAt(position: Int): Wish {
        return wishes[position]
    }

    inner class WishViewHolder(private val binding: ItemWishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(wish: Wish) {
            binding.wishTitle.text = wish.title
            binding.deleteButton.setOnClickListener { onDeleteClick(wish) }
            binding.grantButton.setOnClickListener { onGrantClick(wish) }
        }
    }
}
