package com.example.appnews.network.networkmodel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    val uid : Int = 0,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    var favorite : Boolean = false
) : Parcelable