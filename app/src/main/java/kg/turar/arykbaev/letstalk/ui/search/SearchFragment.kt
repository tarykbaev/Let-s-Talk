package kg.turar.arykbaev.letstalk.ui.search

import android.os.Bundle
import android.view.View
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.databinding.FragmentSearchBinding
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchVM>(SearchVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun performViewBinding() = FragmentSearchBinding.inflate(layoutInflater)
}