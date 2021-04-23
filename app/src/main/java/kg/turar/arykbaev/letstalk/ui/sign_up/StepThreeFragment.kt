package kg.turar.arykbaev.letstalk.ui.sign_up

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepThreeBinding
import kg.turar.arykbaev.letstalk.model.From
import kg.turar.arykbaev.letstalk.model.LearningLanguage
import kg.turar.arykbaev.letstalk.model.NativeLanguage
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search_activity.Option
import kg.turar.arykbaev.letstalk.ui.search_activity.SearchActivity
import org.parceler.Parcels


class StepThreeFragment : BaseFragment<FragmentStepThreeBinding, SignUpVM>(SignUpVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        ui.btnFinsh.setOnClickListener {
            findNavController().navigate(R.id.action_stepThreeFragment_to_verifyMailFragment)
        }

        val country = listOf(From("Kyrgyzstan"), From("Turkey"), From("Kazakhstan"))
        ui.tvCountry.setOnClickListener {
            SearchActivity.show(this, country.map { Option(it, it.description) })
        }

        val nativeLanguage =
            listOf(NativeLanguage("Kyrgyz"), NativeLanguage("Turkish"), NativeLanguage("Kazak"))
        ui.tvLanguage.setOnClickListener {
            SearchActivity.show(this, nativeLanguage.map { Option(it, it.description) })
        }

        val learningLanguage = listOf(
            LearningLanguage("Kyrgyz"),
            LearningLanguage("Turkish"),
            LearningLanguage("Kazak")
        )
        ui.tvLearnLanguage.setOnClickListener {
            SearchActivity.show(this, learningLanguage.map { Option(it, it.description) })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val item = Parcels.unwrap<Any>(data?.getParcelableExtra(SearchActivity.SELECTED_ITEM))
        data?.let {
            when (item) {
                is From -> initSubText(ui.tvCountry, item.description)
                is NativeLanguage -> initSubText(ui.tvLanguage, item.description)
                is LearningLanguage -> initSubText(ui.tvLearnLanguage, item.description)
            }
        }
    }

    private fun initSubText(view: TextView, description: String) {
        view.apply {
            text = description
            setTextColor(ContextCompat.getColor(context, R.color.primary))
        }
    }

    override fun performViewBinding() = FragmentStepThreeBinding.inflate(layoutInflater)
}

//            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
//                mAuth.currentUser.sendEmailVerification().addOnSuccessListener {
//
//                }
//            }