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


class PersonFragment : BaseFragment<FragmentPersonBinding, MainVM>(MainVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun performViewBinding() = FragmentPersonBinding.inflate(layoutInflater)
}