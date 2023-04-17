package uz.behad.elingo.ui.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behad.elingo.data.models.LessonModel
import uz.behad.elingo.domain.repository.Repository

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