package com.example.daggerhiltdemo.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Rating(
    @SerializedName("count")
    val count: Int?, // 120
    @SerializedName("rate")
    val rate: Double? // 3.9
)