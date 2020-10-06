package com.example.jokesapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import io.reactivex.Observable

private const val BASE_URL = "http://api.icndb.com/jokes/"
//enum class MarsApiFilter(val value: String) { SHOW_RENT("rent"), SHOW_BUY("buy"), SHOW_ALL("all") }


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface JokesApiService {

    @GET("random/{number}")
    suspend fun getJokes(@Path("number") number: Int): Response

}

object JokeApi {
    val retrofitService : JokesApiService by lazy { retrofit.create(JokesApiService::class.java) }
}