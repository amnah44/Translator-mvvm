package com.amnah.translator.model.data.languages


import com.google.gson.annotations.SerializedName

data class LanguagesResponseItem(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?
)