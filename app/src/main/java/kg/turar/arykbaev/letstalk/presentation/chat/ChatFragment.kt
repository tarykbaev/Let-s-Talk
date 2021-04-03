package kg.turar.arykbaev.letstalk.presentation.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.extension.action

class ChatFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(action) {
            true -> Toast.makeText(requireActivity(), "Welcome!", Toast.LENGTH_SHORT).show()
            else -> findNavController().navigate(R.id.action_chat_fragment_to_login_fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}