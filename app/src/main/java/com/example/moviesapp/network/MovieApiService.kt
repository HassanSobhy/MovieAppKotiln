package com.example.moviesapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//The Movie db Api
private const val API_KEY = "f7186053daaec4b2c3591d5726d3a328"

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


/**
 * A public interface that exposes the [getMovies] method
 */

interface MovieApiService {


    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ):      // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>

    @GET("top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY
    ):      // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>
}


/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MovieApi {
    val retrofitService: MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}