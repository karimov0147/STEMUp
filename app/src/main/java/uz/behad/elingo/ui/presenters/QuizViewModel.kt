package uz.behad.elingo.ui.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behad.elingo.data.local.DictEntity
import uz.behad.elingo.data.storage.LocaleStorage
import uz.behad.elingo.domain.repository.Repository

class QuizViewModel : ViewModel() {

    val variantsLiveData = MutableLiveData<List<DictEntity>>()
    val pointsLiveData = MutableLiveData<Int>()
    private val repository = Repository.getInstance()

    fun getQuestion() {
        repository.getRandomQuestionList()
            .flowOn(Dispatchers.IO)
            .onEach { variantsLiveData.value = it }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    fun setPoints() {
        LocaleStorage.setPoints(LocaleStorage.getPoints() + 1)
        getPoints()
    }

    fun getPoints() {
        pointsLiveData.value = LocaleStorage.getPoints()
    }
}