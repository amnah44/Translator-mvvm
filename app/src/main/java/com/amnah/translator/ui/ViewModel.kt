package com.amnah.translator.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amnah.translator.model.Repository
import com.amnah.translator.model.data.TranslatorResponse
import com.amnah.translator.model.data.languages.LanguagesResponse
import com.amnah.translator.util.Constant.TAG
import com.amnah.translator.util.State
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()
    val q = MutableLiveData<String?>()
    val source = MutableLiveData<String>()
    val target = MutableLiveData<String>()


    private val _languageList = MutableLiveData<State<LanguagesResponse>>()
    val languageList: LiveData<State<LanguagesResponse>>
        get() = _languageList

    init {
        getLanguageSource()
        getLanguageTarget()
        getLanguagesList()
    }

    private fun getLanguagesList() {
        viewModelScope.launch {
            repository.languages().collect {
                _languageList.postValue(it as State<LanguagesResponse>?)
            }
        }
    }

    val tran = MutableLiveData<State<TranslatorResponse?>?>()

    fun getValue() {
        viewModelScope.launch {
            repository.translator(
                q = q.value.toString(),
                source = source.value,
                target = target.value
            ).collect {
                tran.value = it
                Log.i(TAG,target.value.toString())
                Log.i(TAG,source.value.toString())
                delay(1000)
            }
        }
    }

    private fun getLanguageSource(): String? {
        return languageList.value?.toData()?.filter {
            it.code == source.value
        }?.map { it.code }?.first()

    }
    private fun getLanguageTarget(): String? {
        return languageList.value?.toData()?.filter {
            it.code == target.value
        }?.map { it.code }?.first()

    }
}