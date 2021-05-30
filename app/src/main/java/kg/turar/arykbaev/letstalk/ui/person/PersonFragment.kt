package kg.turar.arykbaev.letstalk.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentPersonBinding
import kg.turar.arykbaev.letstalk.ui.MainVM
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class PersonFragment : BaseFragment<FragmentPersonBinding, PersonVM>(PersonVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        ui.tvPerson.setOnClickListener {
            vm.logout()
            navigateTo(PersonFragmentDirections.toLoginFragment())
        }
    }

    override fun performViewBinding() = FragmentPersonBinding.inflate(layoutInflater)
}