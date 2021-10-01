package com.amnah.translator.model

import com.amnah.translator.model.network.API
import com.amnah.translator.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository {

    fun translator(
        q: String?, source: String?, target: String?
    ) = wrapWithFlow { API.apiService.getTranslator(
        qApi = q, sourceApi = source, targetApi = target) }

    fun languages() = wrapWithFlow { API.apiService.getLanguages() }

    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T?>> {

        return flow {
            emit(State.Loading)
            try {
                val result = function()
                if (result.isSuccessful)
                    emit(State.Success(result.body()))
                else
                    emit(State.Error(result.message()))

            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }
}
