package kg.turar.arykbaev.letstalk.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.extension.action


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.findViewById<TextView>(R.id.tv_login).setOnClickListener {
            action = true
            val navController = findNavController()
            navController.navigate(R.id.action_login_fragment_to_chat_fragment)
        }
        return view
    }
}