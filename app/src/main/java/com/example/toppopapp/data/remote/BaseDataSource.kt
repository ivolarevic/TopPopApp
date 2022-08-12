package com.example.toppopapp.data.remote

import com.example.toppopapp.utils.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Timber.tag("retrofit").d(response.body().toString())
                if (body != null) return Resource.success(body)
            }
            Timber.d(response.message().toString())
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}