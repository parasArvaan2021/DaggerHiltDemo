package com.example.daggerhiltdemo.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class MainReponseItem(
    @SerializedName("category")
    val category: String?, // men's clothing
    @SerializedName("description")
    val description: String?, // Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday
    @SerializedName("id")
    val id: Int?, // 1
    @SerializedName("image")
    val image: String?, // https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg
    @SerializedName("price")
    val price: Double?, // 109.95
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String? // Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops
) {
    fun getTitleNew(): String {
        return title ?: "Demo"

    }
}