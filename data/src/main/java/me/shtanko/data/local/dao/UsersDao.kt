package me.shtanko.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import me.shtanko.data.local.model.UserLocalModel

@Dao
interface UsersDao {

    @Query("SELECT * FROM Users")
    fun getUsers(): Maybe<List<UserLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserLocalModel)
}