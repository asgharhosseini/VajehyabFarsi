package ir.ah.vajehyabfarsi.ui.fragment.favorite

import androidx.lifecycle.Observer
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import dagger.hilt.android.*
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.R
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.databinding.*
import ir.ah.vajehyabfarsi.other.*
import ir.ah.vajehyabfarsi.ui.fragment.favorite.adapter.*
import ir.ah.vajehyabfarsi.ui.fragment.search.adapter.*
import javax.inject.*
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FavoriteViewModel>(
    R.layout.fragment_favorite, FavoriteViewModel::class
), FavoriteAdapter.FavoriteEventListener {
    private val binding by viewBinding(FragmentFavoriteBinding::bind)

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun observeData() {
        super.observeData()
        setUpVajehdapter()
        subscribeToObserveSearch()
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        onItemClick()


    }

    private fun subscribeToObserveSearch() {
        vm.getAllFavorite().observe(viewLifecycleOwner, Observer {
            favoriteAdapter.submitList(it)
        })
    }

    private fun onItemClick() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpVajehdapter() {
        favoriteAdapter.setOnItemClickListener { }
        favoriteAdapter.setOnFavoriteItemEventListener(this)
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onFavorite(vajeh: Vajeh, position: Int, flag: Boolean) {
        if (flag) vm.deleteItem(vajeh.id) else vm.insertVajeh(vajeh)
    }
}