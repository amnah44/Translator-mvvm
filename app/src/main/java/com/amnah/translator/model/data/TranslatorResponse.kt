package com.amnah.translator.model.data


import com.google.gson.annotations.SerializedName

data class TranslatorResponse(
    @SerializedName("translatedText")
    val translatedText: String?
)