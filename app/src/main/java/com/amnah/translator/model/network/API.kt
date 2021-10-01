package com.amnah.translator.model.network

import com.amnah.translator.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

    private val myInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val myClient = OkHttpClient.Builder().addInterceptor(myInterceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(myClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(TranslatorApiService::class.java)

}