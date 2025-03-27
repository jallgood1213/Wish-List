package com.example.wish

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WishViewModel(application: Application) : AndroidViewModel(application) { // ✅ Change ViewModel to AndroidViewModel
    private val repository: WishRepository

    val allWishes: LiveData<List<Wish>>

    init {
        val wishDao = WishDatabase.getDatabase(application).wishDao()
        repository = WishRepository(wishDao)
        allWishes = repository.allWishes
    }

    fun insert(wish: Wish) = viewModelScope.launch {
        repository.insert(wish)
    }

    fun delete(wish: Wish) = viewModelScope.launch {
        repository.delete(wish)
    }

    fun update(wish: Wish) = viewModelScope.launch {
        repository.update(wish)
    }
}
