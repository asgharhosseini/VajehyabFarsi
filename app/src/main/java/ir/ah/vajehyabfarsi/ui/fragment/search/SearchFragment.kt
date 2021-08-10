package ir.ah.vajehyabfarsi.ui.fragment.search

import android.util.*
import android.widget.*
import androidx.core.view.*
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
            vm.searchQuery.value = it.trim()
            binding.btnSearch.isEnabled = it.count { it.isDigit() } < 1
        }

        setUpVajehdapter()

    }

    private fun onClickItem() {
        binding.btnSearch.setOnClickListener {
            vm.validateSearchQuery()
            vm.insertVajehHistory()
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
                        binding.lottieAnimationView.apply {
                            setAnimation(R.raw.search)
                            isVisible=true
                        }
                        binding.message.apply {
                            text=getString(R.string.Searching)
                            isVisible=true
                        }
                    }
                    is Resource.Success -> {
                            if (!event.success.data.results.isNullOrEmpty()
                                && event.success.data.results.size >0){
                                binding.recyclerView.apply {
                                    isVisible=true
                                    vajehAdapter.submitList(event.success.data.results)
                                }
                                binding.lottieAnimationView.apply {
                                    isVisible=false
                                }
                                binding.message.apply {
                                    isVisible=false
                                }
                            }else{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_requested_phrase_was_not_found)
                                    isVisible=true
                                }
                            }
                    }
                    is Resource.Failure -> {
                        when(event.success?.response?.code){
                            400->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_server_is_unable_to_recognize_the_input_parameters)
                                    isVisible=true
                                }
                            }
                            401->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_developer_could_not_be_detected_by_the_server)
                                    isVisible=true
                                }
                            }
                            403->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_server_has_blocked_developer_access_for_obvious_reasons)
                                    isVisible=true
                                }
                            }
                            404->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_requested_phrase_was_not_found)
                                    isVisible=true
                                }
                            }
                            405->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.Method_is_not_allowed)
                                    isVisible=true
                                }
                            }
                            500->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_server_encountered_an_error_and_was_unable_to_execute_the_request)
                                    isVisible=true
                                }
                            }
                            503->{
                                binding.lottieAnimationView.apply {
                                    setAnimation(R.raw.error)
                                    isVisible=true
                                }
                                binding.message.apply {
                                    text=getString(R.string.The_server_is_currently_unavailable)
                                    isVisible=true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onFavorite(vajeh: Vajeh, position: Int, flag: Boolean) {
        if (flag) vm.deleteItem(vajeh.id) else vm.insertVajeh(vajeh)
    }


}
