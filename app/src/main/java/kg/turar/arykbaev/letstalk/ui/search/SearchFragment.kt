package kg.turar.arykbaev.letstalk.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentSearchBinding
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search.adapter.UsersAdapter


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchVM>(SearchVM::class.java), UsersAdapter.Listener {

    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        adapter = UsersAdapter(this)
        adapter.clearItems()
        ui.listUsers.adapter = adapter
        vm.fetchUser(100)
        vm.user.observe(viewLifecycleOwner, {
            it?.let { adapter.addItem(it) }
        })
        setupViews()
    }

    private fun setupViews() {
        ui.toolbarSearch.apply {
            setupWithNavController(findNavController())
            navigationIcon = null
            inflateMenu(R.menu.search_menu)
        }

        val searchItem = ui.toolbarSearch.menu.findItem(R.id.search_user)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println(newText.toString())
                return true
            }
        })
    }

    override fun performViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun onUserClick(user: User) {
        navigateTo(SearchFragmentDirections.toMessageFragment(user))
    }
}