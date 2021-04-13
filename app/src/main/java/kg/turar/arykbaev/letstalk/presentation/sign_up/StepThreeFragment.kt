package kg.turar.arykbaev.letstalk.presentation.sign_up

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepThreeBinding
import kg.turar.arykbaev.letstalk.presentation.base.BaseFragment


class StepThreeFragment : BaseFragment<FragmentStepThreeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.btnFinsh.setOnClickListener {
            findNavController().navigate(R.id.action_stepThreeFragment_to_verifyMailFragment)
        }
    }
    override fun performViewBinding() = FragmentStepThreeBinding.inflate(layoutInflater)
}