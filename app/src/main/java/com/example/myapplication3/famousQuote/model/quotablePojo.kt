package com.example.myapplication3.chuckNorris.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/** Object use for Ui */
data class QuotableUi(
    val content: String,
    val author: String,
    val dateAdded: String,
)

/** Object use for room */
@Entity(tableName = "quotable_quote")
data class QuotableRoom(
    @ColumnInfo(name = "quote_text")
    val content: String,


    @ColumnInfo(name = "quote_author")
    val author: String,

    @ColumnInfo(name = "quote_date")
    val dateAdded: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

/** Object use for retrofit */
data class QuotableRetrofit(
    @Expose
    @SerializedName("content")
    val content: String,


    @Expose
    @SerializedName("author")
    val author: String,

    @Expose
    @SerializedName("dateAdded")
    val dateAdded: String
)
