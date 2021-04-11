package kg.turar.arykbaev.letstalk.presentation.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentChatBinding
import kg.turar.arykbaev.letstalk.presentation.BaseFragment

class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(mAuth.currentUser != null && mAuth.currentUser?.isEmailVerified == true) {
            true -> Toast.makeText(requireActivity(), "Welcome!", Toast.LENGTH_SHORT).show()
            else -> findNavController().navigate(R.id.action_chat_fragment_to_login_fragment)
        }

        setupViews()
    }

    private fun setupViews() {
        ui.tvChat.setOnClickListener {
            mAuth.signOut()
            findNavController().navigate(R.id.action_chat_fragment_to_login_fragment)
        }
    }

    override fun performViewBinding(): FragmentChatBinding {
        return FragmentChatBinding.inflate(layoutInflater)
    }
}