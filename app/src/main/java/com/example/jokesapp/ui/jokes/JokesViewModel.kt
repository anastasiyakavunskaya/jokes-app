package com.example.jokesapp.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.network.JokeApi
import com.example.jokesapp.network.Value
import kotlinx.coroutines.launch


class JokesViewModel : ViewModel() {

    private var _jokes = MutableLiveData<List<Value>>()
    val jokes: LiveData<List<Value>> = _jokes

    fun getJokes(count:Int) {
        viewModelScope.launch {
            try {
                val result = JokeApi.retrofitService.getJokes(count)
                _jokes.value = result.value
            } catch (e: Exception) {
                _jokes.value = ArrayList()
            }
        }
    }
}