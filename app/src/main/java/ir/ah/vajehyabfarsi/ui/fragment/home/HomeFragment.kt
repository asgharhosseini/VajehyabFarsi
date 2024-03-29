package ir.ah.vajehyabfarsi.ui.fragment.home

import android.view.*
import android.widget.*
import androidx.core.view.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.ItemTouchHelper.*
import com.google.android.material.snackbar.*
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

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0, LEFT or RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = adapterHistory.currentList[pos]
            vm.deleteItemHistory(item.id)
            adapterHistory.notifyItemRemoved(pos)
            Snackbar.make(requireView(), getString(R.string.Successfully_deleted_item), Snackbar.LENGTH_LONG).apply {
                setAction(getString(R.string.Undo)) {
                    vm.insertVajehHistory(item)
                    adapterHistory.notifyDataSetChanged()
                }
                show()
            }
        }
    }

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
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
        }
    }

    private fun subscribeToObservers() {
        vm.getAllHistory().observe(viewLifecycleOwner, Observer {
            if (it.size>0){
                adapterHistory.submitList(it)
                binding.lottieAnimationView.isVisible=false
            }else{
                binding.lottieAnimationView.apply {
                    setAnimation(R.raw.start)
                    isVisible=true
                }

            }
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

    private fun showPopMenu(view: View?) {
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