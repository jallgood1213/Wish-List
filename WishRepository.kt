package com.example.wish
import androidx.lifecycle.LiveData
import com.example.wish.Wish
import com.example.wish.WishDao

class WishRepository(private val wishDao: WishDao) {

    val allWishes: LiveData<List<Wish>> = wishDao.getAllWishes()

    suspend fun insert(wish: Wish) {
        wishDao.insertWish(wish)
    }

    suspend fun delete(wish: Wish) {
        wishDao.deleteWish(wish)
    }

    suspend fun update(wish: Wish) {
        wishDao.updateWish(wish)
    }
}