package com.example.applemarketsolution

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyItem(
    val image: Int,
    val title: String,
    val postDetail: String,
    val seller: String,
    val price: Int,
    val address: String,
    var likes: Int,
    val replies: Int,
    var likePressed: Boolean
) : Parcelable