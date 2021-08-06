package ir.ah.vajehyabfarsi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.databinding.*
import ir.ah.vajehyabfarsi.other.wrapper.*

import kotlin.reflect.KClass

abstract class BaseFragment<viewModel : BaseViewModel>(
    @LayoutRes val layoutRes: Int,
    private val vmClass: KClass<viewModel>
) : Fragment(layoutRes) {

    val mainActivity: MainActivity? = runCatching {
        requireActivity() as MainActivity
    }.getOrNull()

    private val viewGroup get() = runCatching { (view as ViewGroup) }.getOrNull()
    var isNoInternetLayoutShowing = false
        private set

    private var _noInternetLayoutBinding: NoInternetLayoutBinding? = null
    private val noInternetLayoutBinding get() = _noInternetLayoutBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _noInternetLayoutBinding = NoInternetLayoutBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _noInternetLayoutBinding = null
    }

    @MainThread
    private fun viewModels(
        ownerProducer: () -> ViewModelStoreOwner = { this },
        factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ) = createViewModelLazy(vmClass, { ownerProducer().viewModelStore }, factoryProducer)

    open fun getViewModelStoreOwner(): ViewModelStoreOwner = this
    protected val vm: viewModel by viewModels({ getViewModelStoreOwner() })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    open fun setUpViews() {}
    open fun observeData() {}

    /**
     * [resource] a type of [Resource]
     * [retry] retry called function
     * */
    fun <T> handleResource(
        resource: Resource<T>,
        retry: (() -> Unit)? = null,
    ) {
        when (resource) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                hideNoInternetLayout()
            }
            is Resource.Failure -> {
                when (resource.failure) {
                    is ApiCallFailure.NoInternet -> {
                        retry?.let { showNoInternetLayout(it) }
                    }
                    is ApiCallFailure.Unauthorized -> {
                        // FIXME: 6/28/2021: navigate to auth fragment with global action (or can receive from function arguments)
                        //  and clear db..
                    }
                    is ApiCallFailure.OtherError -> {
                        //FIXME: 6/28/2021: handle other errors [SealedCallResponse] or show the message or...
                    }
                }
            }

        }
    }

    private fun showNoInternetLayout(retry: (() -> Unit)) {
        if (!isNoInternetLayoutShowing)
            viewGroup?.let { viewGroup ->
                viewGroup.addView(noInternetLayoutBinding.root)
                noInternetLayoutBinding.btnRetry.setOnClickListener { retry.invoke() }
                isNoInternetLayoutShowing = true
            }
    }

    private fun hideNoInternetLayout() {
        if (isNoInternetLayoutShowing)
            viewGroup?.let { viewGroup ->
                viewGroup.removeView(noInternetLayoutBinding.root)
                isNoInternetLayoutShowing = false
            }
    }
}