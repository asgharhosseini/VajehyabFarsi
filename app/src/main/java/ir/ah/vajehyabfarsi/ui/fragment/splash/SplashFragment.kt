package ir.ah.vajehyabfarsi.ui.fragment.splash

import androidx.core.view.*
import androidx.lifecycle.*
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
import kotlinx.coroutines.*
import javax.inject.*
@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(
    R.layout.fragment_splash, SplashViewModel::class
) {
    private val binding by viewBinding(FragmentSplashBinding::bind)


    override fun observeData() {
        super.observeData()
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }

    override fun setUpViews() {
        super.setUpViews()

    }


}