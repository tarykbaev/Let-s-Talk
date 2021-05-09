package kg.turar.arykbaev.letstalk.ui.message

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.text.parseAsHtml
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentCorrectionBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.extension.hideKeyboard
import kg.turar.arykbaev.letstalk.extension.invisible
import kg.turar.arykbaev.letstalk.extension.toText
import kg.turar.arykbaev.letstalk.extension.visible
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import java.lang.StringBuilder


class CorrectionFragment : BaseFragment<FragmentCorrectionBinding, MessageVM>(MessageVM::class.java) {

    private val args: CorrectionFragmentArgs by navArgs()
    private lateinit var menu: Menu
    private var isSending = false
    private val mainText = StringBuilder()
    private val correctText = StringBuilder()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setMessage()
        setupViews()
        subscribeToLiveData()
    }

    private fun setMessage() {
        vm.correctionMessage = args.message
        vm.user = args.user
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner, {
            when (it) {
                is Event.Notification -> {
                }
                is Event.Success -> {
                }
            }
        })
    }

    private fun setupViews() {
        setupToolbar()
        ui.apply {
            tvMain.text = vm.getMessageText()
            tvMain.setOnClickListener { editInput() }
            imgCorrect.setOnClickListener { editInput() }
            inputCorrection.addTextChangedListener { toggleMenuItem() }
            imgClear.setOnClickListener { clearEditInput() }
        }
    }

    private fun clearEditInput() {
        ui.inputCorrection.apply {
            setText("")
            invisible()
        }
        ui.imgClear.invisible()
        ui.imgCorrect.visible()
        ui.tvMain.text = vm.getMessageText()
        hideMenu()
        hideKeyboard()
    }

    private fun editInput() {
        ui.imgCorrect.invisible()
        ui.inputCorrection.apply {
            visible()
            setText(vm.getMessageText())
            requestFocus()
        }
        ui.imgClear.visible()
        isSending = false
    }

    private fun setupToolbar() {
        ui.toolbarCorrection.apply {
            setupWithNavController(findNavController())
            setNavigationIcon(R.drawable.ic_close)
            inflateMenu(R.menu.correction)
            setOnMenuItemClickListener { onMenuItemSelected(it) }
        }
        menu = ui.toolbarCorrection.menu
    }

    private fun onMenuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ok -> okClick()
            R.id.send -> sendMessage()
        }
        return true
    }

    private fun okClick() {
        isSending = true
        showMenu()
        hideKeyboard()
        ui.inputCorrection.clearFocus()
        correction()
    }

    private fun sendMessage() {
        val message = "$mainText <br> $correctText"
        vm.sendMessage(message, "text")
        findNavController().navigateUp()
    }

    private fun correction() {
        val mainList = vm.getMessageText().split(" ")
        val correctList = ui.inputCorrection.toText.split(" ")

        var count = 0
        mainList.forEach {
            val index = correctList.indexOf(it)
            if (index > -1) mainText.append(it)
            else mainText.append("<font color=#FF0000><del>$it</del></font>")
            if (++count < mainList.size) mainText.append(" ")
        }

        count = 0
        correctList.forEach {
            val index = mainList.indexOf(it)
            if (index > -1) correctText.append(it)
            else correctText.append("<font color=#32CD32>$it</font>")
            if (++count < correctList.size) correctText.append(" ")
        }

        ui.tvMain.text = mainText.toString().parseAsHtml()
        ui.inputCorrection.setText(correctText.toString().parseAsHtml())
    }

    private fun showMenu(isOk: Boolean = false) {
        if (isOk) {
            menu.findItem(R.id.send).isVisible = false
            menu.findItem(R.id.ok).isVisible = true
        } else {
            menu.findItem(R.id.send).isVisible = true
            menu.findItem(R.id.ok).isVisible = false
        }
    }

    private fun hideMenu() {
        menu.findItem(R.id.send).isVisible = false
        menu.findItem(R.id.ok).isVisible = false
    }

    private fun toggleMenuItem() {
        if (isValid) menu.findItem(R.id.ok).isVisible = true
        menu.findItem(R.id.send).isVisible = isSending
        isSending = false
    }

    private val isValid: Boolean
        get() = ui.inputCorrection.toText != vm.getMessageText() && !isSending

    override fun performViewBinding() = FragmentCorrectionBinding.inflate(layoutInflater)
}