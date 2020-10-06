package com.example.jokesapp.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Value (
    val id: String,
    val joke: String,
    val categories: Array<String>
): Serializable

data class Response(
    val type: String,
    val value: List<Value>
):Serializable