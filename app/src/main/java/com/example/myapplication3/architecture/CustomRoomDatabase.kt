package com.example.myapplication3.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication3.chuckNorris.model.QuotableRoom
import com.example.myapplication3.famousQuote.dao.QuotableDao

@Database(
    entities = [
        QuotableRoom::class
    ],
    version = 9,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {

    abstract fun QuotableDao(): QuotableDao

}
