package kg.turar.arykbaev.letstalk.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.databinding.FragmentSearchBinding
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.MainVM
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search.adapter.UsersAdapter


class SearchFragment : BaseFragment<FragmentSearchBinding, MainVM>(MainVM::class.java), UsersAdapter.Listener {

    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        val options = vm.getRecyclerOptions()
        adapter = UsersAdapter(options, this)

        ui.listUsers.adapter = adapter
        adapter.startListening()
    }

    override fun performViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun onUserClick(user: User) {
        navigateTo(SearchFragmentDirections.toMessageFragment(user))
    }
}