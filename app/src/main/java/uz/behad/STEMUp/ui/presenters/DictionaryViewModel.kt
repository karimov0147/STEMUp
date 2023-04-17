package uz.behad.STEMUp.ui.presenters

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behad.STEMUp.domain.repository.Repository

class DictionaryViewModel : ViewModel() {

    val loadListLiveData = MutableLiveData<Cursor>()
    var searchResultLiveData: MutableLiveData<Pair<Cursor, String>> = MutableLiveData()
    private val repository: Repository = Repository.getInstance()


    fun loadList() {
        var cursor: Cursor? = null
        repository.getAllTerms()
            .onEach { cursor = it }
            .flowOn(Dispatchers.IO)
            .onEach { loadListLiveData.value = cursor ?: return@onEach }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    fun searchWord(query: String) {
        repository.searchWithQuery("%$query%")
            .flowOn(Dispatchers.IO)
            .onEach { searchResultLiveData.value = Pair(it, query) }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

}