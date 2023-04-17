package com.hackerstreet.stemup.ui.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackerstreet.stemup.data.models.LessonModel
import com.hackerstreet.stemup.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LessonViewModel : ViewModel() {
    private val repository = Repository.getInstance()
    val lessonListLivedata = MutableLiveData<List<LessonModel>>()

    fun getLesson(start: Int, end: Int) {
        repository.getLesson(start, end)
            .flowOn(Dispatchers.IO)
            .onEach { lessonListLivedata.value = it }
            .launchIn(viewModelScope)
    }


}