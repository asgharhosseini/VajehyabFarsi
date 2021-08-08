package ir.ah.vajehyabfarsi.ui.fragment.home

import androidx.navigation.fragment.*
import dagger.hilt.android.*
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.R
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.databinding.*
import ir.ah.vajehyabfarsi.other.*

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewModel>(R.layout.fragment_home, HomeViewModel::class) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun observeData() {
        super.observeData()
    }

    override fun setUpViews() {
        super.setUpViews()
        onClickItem()
    }

    private fun onClickItem() {
        binding.searchBox.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment(null))
        }

    }
}