package ir.ah.vajehyabfarsi.ui.fragment.search

import android.util.*
import android.widget.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import dagger.hilt.android.*
import ir.ah.vajehyabfarsi.R
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.databinding.*
import ir.ah.vajehyabfarsi.other.*
import ir.ah.vajehyabfarsi.other.util.*
import ir.ah.vajehyabfarsi.other.util.Constance.TAG
import ir.ah.vajehyabfarsi.other.util.Constance.filterName
import ir.ah.vajehyabfarsi.other.wrapper.*
import ir.ah.vajehyabfarsi.ui.fragment.search.adapter.*
import kotlinx.coroutines.flow.*
import javax.inject.*


@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchViewModel>(R.layout.fragment_search, SearchViewModel::class),
    VajehAdapter.FavoriteEventListener {
    private val binding by viewBinding(FragmentSearchBinding::bind)

    @Inject
    lateinit var vajehAdapter : VajehAdapter


    override fun observeData() {
        super.observeData()
        subscribeToObserveSearch()
    }

    override fun setUpViews() {
        super.setUpViews()
        setUpFilterAdapter()
        onClickItem()
        binding.etSearch.afterTextChanged { it ->
            vm.searchQuery.value = it
            binding.btnSearch.isEnabled = it.count { it.isDigit() } < 1
        }
        setUpVajehdapter()

    }

    private fun onClickItem() {
        binding.btnSearch.setOnClickListener {
            vm.validateSearchQuery()

        }
    }

    private fun setUpVajehdapter() {
        vajehAdapter.setOnItemClickListener { }
        vajehAdapter.setOnFavoriteItemEventListener(this)
        binding.recyclerView.apply {
            adapter = vajehAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setUpFilterAdapter() {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_filer, filterName)
        binding.searchFilter.setAdapter(adapter)
        binding.searchFilter.setOnItemClickListener { adapterView, view, position, l ->
            vm.filter.value = filterNameToFilterId(adapter.getItem(position).toString())
        }
        binding.searchFilter.setText(adapter.getItem(0), false)
        vm.filter.value = filterNameToFilterId(adapter.getItem(0).toString())
    }


    private fun subscribeToObserveSearch() {
        lifecycleScope.launchWhenStarted {
            vm.searchEvent.collectLatest { event ->
                when (event) {
                    is SearchEvent.searchQueryIsEmpty -> {
                        binding.textInputLayoutSearch
                            .error = getString(R.string.search_box_empty)
                    }
                    is SearchEvent.ShowError -> {
                    }

                }
            }

        }
        lifecycleScope.launchWhenStarted {
            vm.searchResponse.collectLatest { event ->
                handleResource(event) {
                    vm.validateSearchQuery()
                }
                when (event) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        vajehAdapter.submitList(event.success.data.results)
                        Log.e(TAG, event.success.data.results[0].title)
                    }
                    is Resource.Failure -> {
                    }
                }
            }
        }
    }

    override fun onFavorite(vajeh: Vajeh, position: Int, flag: Boolean) {
        if (flag) vm.deleteItem(vajeh.id) else vm.insertVajeh(vajeh)
    }


}
