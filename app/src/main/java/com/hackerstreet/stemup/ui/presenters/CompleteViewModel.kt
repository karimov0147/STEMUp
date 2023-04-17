package com.hackerstreet.stemup.ui.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackerstreet.stemup.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompleteViewModel : ViewModel() {
    private val repository = Repository.getInstance()

    fun updateAnswer(currentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLesson(currentId)
        }
    }
}