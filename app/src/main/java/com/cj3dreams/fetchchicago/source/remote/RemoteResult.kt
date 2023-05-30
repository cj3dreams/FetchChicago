package com.cj3dreams.fetchchicago.source.remote

import java.lang.Exception

sealed class RemoteResult<out T> {

    data class Success<out T>(val data: T):RemoteResult<T>()
    data class Error(val exception: Exception): RemoteResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}