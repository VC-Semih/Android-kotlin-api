package com.example.myapplication3.famousQuote.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication3.chuckNorris.model.QuotableRoom

@Dao
interface QuotableDao {
    @Query("SELECT * FROM quotable_quote")
    fun selectAll() : LiveData<List<QuotableRoom>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quotableRoom: QuotableRoom)


    @Query("DELETE FROM quotable_quote")
    fun deleteAll()
}