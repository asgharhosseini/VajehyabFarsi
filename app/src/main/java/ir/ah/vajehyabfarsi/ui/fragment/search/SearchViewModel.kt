package ir.ah.vajehyabfarsi.ui.fragment.search

import android.util.*
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.other.wrapper.*
import ir.ah.vajehyabfarsi.repository.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class SearchViewModel @Inject constructor(
    val mainCoroutineDispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository,
) : BaseViewModel(mainCoroutineDispatcher) {
    val searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val filter: MutableStateFlow<String> = MutableStateFlow("")

    private val searchEventChannel = Channel<SearchEvent>()
    val searchEvent = searchEventChannel.receiveAsFlow()

    private val searchResponseChannel = Channel<Resource<VajehResponse>>()
    val searchResponse = searchResponseChannel.receiveAsFlow()

    fun validateSearchQuery() {

        val searchQuery = searchQuery.value
        val filter = filter.value
        doInMain {
            if (searchQuery.isEmpty()) {
                searchEventChannel.send(SearchEvent.searchQueryIsEmpty)
                return@doInMain
            }

            getSearchVajeh()
            return@doInMain
        }
    }

    fun getSearchVajeh() {
        val searchQuery = this.searchQuery.value
        val filter = this.filter.value
        doInMain {
            searchResponseChannel.send(Resource.Loading)
            searchResponseChannel.send(searchRepository.getWord(searchQuery, filter))
        }
    }
}




