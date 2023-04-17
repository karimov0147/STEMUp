package com.hackerstreet.stemup.ui.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.hackerstreet.stemup.domain.repository.Repository

class LevelsViewModel : ViewModel() {
    private val repository = Repository.getInstance()
    val roadMapLiveData = MutableLiveData<List<Pair<Int, Int>>>()

    fun getRoadMap() {
        repository.getRoadMap()
            .flowOn(Dispatchers.IO)
            .onEach { roadMapLiveData.value = it }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }


}