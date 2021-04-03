package kg.turar.arykbaev.letstalk.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.presentation.BaseFragment


class LoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val button = view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            val email = view.findViewById<EditText>(R.id.input_email).text.toString()
            val password = view.findViewById<EditText>(R.id.input_password).text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        findNavController().navigate(R.id.action_login_fragment_to_chat_fragment)
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(requireContext(), "Wrong!", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }
}