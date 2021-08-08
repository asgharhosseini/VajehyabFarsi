package ir.ah.vajehyabfarsi.ui.fragment.search

import ir.ah.vajehyabfarsi.other.wrapper.*

sealed class SearchEvent {

    object searchQueryIsEmpty : SearchEvent()
    data class ShowError(val failure: FailureInterface?) : SearchEvent()
}
