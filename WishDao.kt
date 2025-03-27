package com.example.wish
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWish(wish: Wish)

    @Delete
    suspend fun deleteWish(wish: Wish)

    @Update
    suspend fun updateWish(wish: Wish)

    @Query("SELECT * FROM wish_table ORDER BY id DESC")
    fun getAllWishes(): LiveData<List<Wish>>
}