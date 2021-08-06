package ir.ah.vajehyabfarsi.base

import androidx.lifecycle.*
import kotlinx.coroutines.*

abstract class BaseViewModel(private val mainCoroutineDispatcher: CoroutineDispatcher) :
    ViewModel() {

    fun doInMain(action: suspend () -> Unit) =
        viewModelScope.launch(mainCoroutineDispatcher) { action.invoke() }
}