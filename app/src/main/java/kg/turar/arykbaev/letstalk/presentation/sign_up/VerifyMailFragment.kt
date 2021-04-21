package kg.turar.arykbaev.letstalk.presentation.sign_up

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentVerifyMailBinding
import kg.turar.arykbaev.letstalk.presentation.base.BaseFragment

class VerifyMailFragment : BaseFragment<FragmentVerifyMailBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_verifyMailFragment_to_login_fragment)
        }
        ui.btnOpenApp.setOnClickListener {
            findNavController().navigate(R.id.action_verifyMailFragment_to_login_fragment)
        }

//            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
//                mAuth.currentUser.sendEmailVerification().addOnSuccessListener {
//
//                }
//            }

    }
    override fun performViewBinding() = FragmentVerifyMailBinding.inflate(layoutInflater)
}