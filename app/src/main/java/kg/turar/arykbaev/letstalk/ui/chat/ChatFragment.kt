package kg.turar.arykbaev.letstalk.ui.chat

import android.os.Bundle
import android.view.View
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentChatBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.ui.MainVM
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class ChatFragment : BaseFragment<FragmentChatBinding, MainVM>(MainVM::class.java) {

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
                is Event.Notification -> showWarningSnackbar(it.message)
                is Event.Unauthorized -> navigate(R.id.action_chat_fragment_to_login_fragment)
                is Event.Success -> vm.currentUser = it.data as User
            }
        })
    }

    private fun setupViews() {
        ui.tvChat.setOnClickListener {
            vm.logout()
            navigate(R.id.action_chat_fragment_to_login_fragment)
        }
    }

    override fun performViewBinding(): FragmentChatBinding {
        return FragmentChatBinding.inflate(layoutInflater)
    }
}