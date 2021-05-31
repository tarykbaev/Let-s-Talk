package kg.turar.arykbaev.letstalk.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentChatBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search.adapter.UsersAdapter


class ChatFragment : BaseFragment<FragmentChatBinding, ChatVM>(ChatVM::class.java), UsersAdapter.Listener {

    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribeToLiveData()
        checkAuthentication()
        vm.initUser()
    }

    private fun checkAuthentication() {
        vm.checkAuthentication()
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner, {
            when(it) {
                is Event.Notification -> {}
                is Event.Unauthorized -> navigate(R.id.action_chat_fragment_to_login_fragment)
                is Event.Success -> vm.currentUser = it.data as User
            }
        })
    }

    private fun setupViews() {
        adapter = UsersAdapter(this)
        ui.listChat.adapter = adapter
        ui.toolbarChat.apply {
            setupWithNavController(findNavController())
            inflateMenu(R.menu.search_menu)
        }
        val searchItem = ui.toolbarChat.menu.findItem(R.id.search_user)
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
        adapter.clearItems()
        vm.fetchChatUser(100)
        vm.chatUser.observe(viewLifecycleOwner, {
            it?.let { adapter.addItem(it) }
            Log.d("ChatFragment", it.toString())
        })
    }

    override fun onUserClick(user: User) {
        navigateTo(ChatFragmentDirections.toMessageFragment(user))
    }

    override fun performViewBinding(): FragmentChatBinding = FragmentChatBinding.inflate(layoutInflater)
}