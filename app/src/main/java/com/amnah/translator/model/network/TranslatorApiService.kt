package com.amnah.translator.model.network

import com.amnah.translator.model.data.TranslatorResponse
import com.amnah.translator.model.data.languages.LanguagesResponse
import com.amnah.translator.model.data.languages.LanguagesResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslatorApiService {

    @POST("translate")
    suspend fun getTranslator(
        @Query("q") qApi:String?,
        @Query("source") sourceApi: String?,
        @Query("target") targetApi: String?
    ): Response<TranslatorResponse>


    @GET("languages")
    suspend fun getLanguages(): Response<LanguagesResponse>

}