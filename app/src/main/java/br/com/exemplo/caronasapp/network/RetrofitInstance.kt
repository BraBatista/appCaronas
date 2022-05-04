package br.com.exemplo.caronasapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder().add( KotlinJsonAdapterFactory() ).build()

class RetrofitInstance {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl( "https://apicaronas.herokuapp.com/" ) // URL base
            .addConverterFactory( MoshiConverterFactory.create(moshi) ) // Conversor
            .build() // Cria o Retrofit Object
            .create( ApiService::class.java ) // Interface
    }
}