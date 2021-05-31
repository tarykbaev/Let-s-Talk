package kg.turar.arykbaev.letstalk.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentPersonBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.extension.negativeButton
import kg.turar.arykbaev.letstalk.extension.positiveButton

import kg.turar.arykbaev.letstalk.extension.setImageByUrl
import kg.turar.arykbaev.letstalk.extension.showDialog
import kg.turar.arykbaev.letstalk.ui.MainVM
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class PersonFragment : BaseFragment<FragmentPersonBinding, MainVM>(MainVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        vm.initUser()
        setupView()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner, {
            when(it) {
                is Event.Success -> { initUser(it.data) }
            }
        })
    }

    private fun initUser(data: Any?) {
        val user = data as? User ?: User()
        ui.imgPerson.setImageByUrl(user.image_url)
        ui.tvName.text = user.name
        ui.tvDepartment.text = user.department
        ui.tvYear.text = user.grade
        ui.tvCountry.text = user.from
        ui.tvNativeLan.text = user.nativeLang
        ui.tvLearnLan.text = user.learningLang
    }

    private fun setupView() {
        ui.toolbarProfile.apply {
            setupWithNavController(findNavController())
            inflateMenu(R.menu.logout_menu)
            setOnMenuItemClickListener { onMenuItemSelected(it) }
            title = null
            navigationIcon = null
        }
    }

    private fun onMenuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> logout()
        }
        return true
    }

    private fun logout() {
        showDialog {
            setTitle("Sign Out")
            setMessage("Do you really want to leave?")
            positiveButton {
                vm.logout()
                navigateTo(PersonFragmentDirections.toLoginFragment())
            }
            negativeButton { }
        }
    }

    override fun performViewBinding() = FragmentPersonBinding.inflate(layoutInflater)
}