package ir.ah.vajehyabfarsi.ui.fragment.home

import dagger.hilt.android.lifecycle.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.repository.history.*
import ir.ah.vajehyabfarsi.repository.search.*
import ir.ah.vajehyabfarsi.ui.fragment.home.adapter.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class HomeViewModel @Inject constructor(
    val mainCoroutineDispatcher: CoroutineDispatcher,
    private val historyRepository: HistoryRepository,
) : BaseViewModel(mainCoroutineDispatcher) {



    fun getAllHistory() =historyRepository.getAllHistory()

}




