package uz.behad.STEMUp.ui.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.behad.STEMUp.domain.repository.Repository

class CompleteViewModel : ViewModel() {
    private val repository = Repository.getInstance()

    fun updateAnswer(currentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLesson(currentId)
        }
    }
}