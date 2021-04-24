package kg.turar.arykbaev.letstalk.ui.sign_up

import android.content.Intent
import android.os.Bundle
import android.util.EventLog
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepThreeBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.extension.showMessage
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.model.From
import kg.turar.arykbaev.letstalk.model.LearningLanguage
import kg.turar.arykbaev.letstalk.model.NativeLanguage
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search_activity.Option
import kg.turar.arykbaev.letstalk.ui.search_activity.SearchActivity
import org.parceler.Parcels


class StepThreeFragment : BaseFragment<FragmentStepThreeBinding, SignUpVM>(SignUpVM::class.java) {

    private val args: StepThreeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        setUser()
        setupViews()
        subscribeLiveData()
    }

    private fun subscribeLiveData() {
        vm.event.observe(viewLifecycleOwner, {
            when(it) {
                is Event.Notification -> showWarningSnackbar(it.message, ui.btnFinsh)
                is Event.Success -> navigate(R.id.action_stepThreeFragment_to_verifyMailFragment)
                is Event.Loading -> showMessage("Loading...")
            }
        })
    }

    private fun setUser() {
        vm.user = args.userData
    }

    private fun setupViews() {
        ui.btnFinsh.setOnClickListener {
            vm.register(vm.user)
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
                is From -> {
                    initSubText(ui.tvCountry, item.description)
                    vm.user.from = item.description
                }
                is NativeLanguage -> {
                    initSubText(ui.tvLanguage, item.description)
                    vm.user.nativeLang = item.description
                }
                is LearningLanguage -> {
                    initSubText(ui.tvLearnLanguage, item.description)
                    vm.user.learningLang = item.description
                }
            }
        }
        toggleButtonState()
    }

    private fun initSubText(view: TextView, description: String) {
        view.apply {
            text = description
            setTextColor(ContextCompat.getColor(context, R.color.primary))
        }
    }

    private fun toggleButtonState() {
        ui.btnFinsh.isEnabled = vm.allSelected()
    }

    override fun performViewBinding() = FragmentStepThreeBinding.inflate(layoutInflater)
}