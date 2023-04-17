package uz.behad.elingo.domain.repository

import android.database.Cursor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.behad.elingo.data.local.AppDatabase
import uz.behad.elingo.data.local.DictEntity
import uz.behad.elingo.data.models.LessonModel
import uz.behad.elingo.data.storage.LocaleStorage
import uz.behad.elingo.data.storage.LocaleStorage.toLessonModel

class Repository {

    companion object {
        private var mainRepository: Repository? = null

        fun initRepository() {
            mainRepository = Repository()
        }

        fun getInstance() = mainRepository!!
    }

    private val dictDao = AppDatabase.INSTANCE?.dictDAO()!!

    fun getAllTerms(): Flow<Cursor> = flow {
        emit(dictDao.getAllTerms(LocaleStorage.getCategory()))
    }

    fun searchWithQuery(query: String) = flow {
        emit(dictDao.getSearchList(query, LocaleStorage.getCategory()))
    }

    fun getRandomQuestionList(): Flow<List<DictEntity>> = flow {
        emit(dictDao.getRandomTermsList(LocaleStorage.getCategory()))
    }

    fun getLesson(start: Int, end: Int): Flow<List<LessonModel>> = flow {
        when (LocaleStorage.getCategory()) {
            1 -> {
                emit(dictDao.getScienseLesson(start, end).map { it.toLessonModel() })
            }
            2 -> {
                emit(dictDao.getTechLesson(start, end).map { it.toLessonModel() })
            }
            3 -> {
                emit(dictDao.getEngineLesson(start, end).map { it.toLessonModel() })
            }
            4 -> {
                emit(dictDao.getMathLesson(start, end).map { it.toLessonModel() })
            }
        }
    }

    fun getRoadMap(): Flow<List<Pair<Int, Int>>> = flow {
        when (LocaleStorage.getCategory()) {
            1 -> {
                emit(dictDao.getScienseRoadMap().map { Pair(it.id / 5, it.isFinished) })
            }
            2 -> {
                emit(dictDao.getTechRoadMap().map { Pair(it.id / 5, it.isFinished) })
            }
            3 -> {
                emit(dictDao.getEngineRoadMap().map { Pair(it.id / 5, it.isFinished) })
            }
            4 -> {
                emit(dictDao.getMathRoadMap().map { Pair(it.id / 5, it.isFinished) })
            }
        }
    }

    fun updateLesson(currentId: Int) {
        when (LocaleStorage.getCategory()) {
            1 -> {
                dictDao.updateScienseTest(currentId, 1)
            }
            2 -> {
                dictDao.updateTechTest(currentId, 1)
            }
            3 -> {
                dictDao.updateEngineTest(currentId, 1)
            }
            4 -> {
                dictDao.updateMathTest(currentId, 1)
            }
        }
    }


}