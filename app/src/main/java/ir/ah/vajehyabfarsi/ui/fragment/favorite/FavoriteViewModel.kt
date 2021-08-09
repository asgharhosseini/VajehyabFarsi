package ir.ah.vajehyabfarsi.ui.fragment.favorite

import dagger.hilt.android.lifecycle.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.repository.history.*
import ir.ah.vajehyabfarsi.repository.search.*
import ir.ah.vajehyabfarsi.ui.fragment.home.adapter.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val mainCoroutineDispatcher: CoroutineDispatcher,
    private val repository: SearchRepository,
) : BaseViewModel(mainCoroutineDispatcher) {

    fun insertVajeh(vajeh: Vajeh) = doInMain { repository.insertVajeh(vajeh) }
    fun deleteItem(id: String) = doInMain { repository.deleteItem(id) }
    fun getAllFavorite() =repository.getAllFavorite()


}




