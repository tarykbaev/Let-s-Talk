package kg.turar.arykbaev.letstalk.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kg.turar.arykbaev.letstalk.extension.hideKeyboard
import javax.inject.Inject

abstract class BaseFragment<DataBinding : ViewBinding, ViewModel : BaseVM>
    (private var viewModelClass: Class<ViewModel>) : Fragment() {

    protected lateinit var ui: DataBinding
    protected lateinit var vm: ViewModel
    @Inject protected lateinit var vmFactory: ViewModelProvider.Factory

    abstract fun performViewBinding(): DataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, vmFactory).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = performViewBinding()
        return ui.root
    }

    fun navigateTo(navDirections: NavDirections) = findNavController().navigate(navDirections)

    fun navigate(@IdRes idRes: Int) = findNavController().navigate(idRes)

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.event.value = null
    }
}