package ir.ah.vajehyabfarsi.ui.fragment.home

import android.view.*
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import dagger.hilt.android.*
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.R
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.databinding.*
import ir.ah.vajehyabfarsi.other.*
import ir.ah.vajehyabfarsi.ui.fragment.home.adapter.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewModel>(R.layout.fragment_home, HomeViewModel::class),HistoryAdapter.DeleteEventListener {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var adapterHistory: HistoryAdapter

    override fun observeData() {
        super.observeData()
        setUpHistoryAdapter()
        subscribeToObservers()
    }

    override fun setUpViews() {
        super.setUpViews()
        onClickItem()

    }
    private fun setUpHistoryAdapter() {
        adapterHistory.setOnItemClickListener { }
        adapterHistory.setOnDeleteItemEventListener(this)
        binding.rvHome.apply {
            adapter = adapterHistory
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun subscribeToObservers() {
        vm.getAllHistory().observe(viewLifecycleOwner, Observer {
            adapterHistory.submitList(it)
        })
    }
    private fun onClickItem() {
        binding.searchBox.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment(null))
        }
        binding.menu.setOnClickListener {
            showPopMenu(it)
        }

    }

    private fun showPopMenu(it: View?) {
        val popup = PopupMenu(requireContext(), view)
        val inflater: MenuInflater = popup.getMenuInflater()
        inflater.inflate(R.menu.favorite_menu, popup.getMenu())
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.inFavorite -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFavoriteFragment())

                }

            }
            true
        })

        popup.show()
    }

    override fun onDelete(history: History, position: Int) {

    }
}