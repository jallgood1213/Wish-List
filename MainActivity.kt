package com.example.wish

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wish.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: WishViewModel by viewModels { WishViewModelFactory(application) } // ✅ Corrected ViewModel initialization
    private lateinit var adapter: WishAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize adapter with lambda functions for delete and grant wish
        adapter = WishAdapter(
            mutableListOf(),
            onDeleteClick = { wish -> viewModel.delete(wish) },
            onGrantClick = { wish ->
                val updatedWish = wish.copy(isGranted = !wish.isGranted) // Toggle wish status
                viewModel.update(updatedWish)
            }
        )

        // Set up RecyclerView
        binding.wishRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        // Observe the LiveData from ViewModel
        viewModel.allWishes.observe(this) { wishes ->
            adapter.updateWishes(wishes.toMutableList())
        }

        // Add new wish when button is clicked
        binding.addButton.setOnClickListener {
            val title = binding.wishEditText.text.toString().trim()
            if (title.isNotEmpty()) {
                viewModel.insert(Wish(title = title)) // ✅ Ensure Wish class matches
                binding.wishEditText.text.clear()
            }
        }

        // Swipe to delete functionality
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val wishToDelete = adapter.getWishAt(position) // ✅ Ensure this method exists in WishAdapter
                viewModel.delete(wishToDelete)
            }
        }

        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.wishRecyclerView)
    }
}
