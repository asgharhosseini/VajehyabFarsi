package ir.ah.vajehyabfarsi.ui.fragment.home

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.other.wrapper.*
import ir.ah.vajehyabfarsi.repository.*
import ir.ah.vajehyabfarsi.ui.fragment.search.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class HomeViewModel @Inject constructor(
    val mainCoroutineDispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository,
) : BaseViewModel(mainCoroutineDispatcher) {

}




