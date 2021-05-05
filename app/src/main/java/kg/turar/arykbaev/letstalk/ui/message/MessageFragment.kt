package kg.turar.arykbaev.letstalk.ui.message

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.databinding.FragmentMessageBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.domain.models.Message
import kg.turar.arykbaev.letstalk.extension.invisible
import kg.turar.arykbaev.letstalk.extension.setImageByUrl
import kg.turar.arykbaev.letstalk.extension.toText
import kg.turar.arykbaev.letstalk.extension.visible
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.message.adapter.MessageAdapter


class MessageFragment : BaseFragment<FragmentMessageBinding, MessageVM>(MessageVM::class.java), MessageAdapter.Listener {

    private val args: MessageFragmentArgs by navArgs()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setUser()
        setupViews()
        subscribeToLiveData()
    }

    private fun setUser() {
        vm.user = args.user
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner, {
            when(it) {
                is Event.Notification -> {}
                is Event.Success -> {}
            }
        })
        vm.message.observe(viewLifecycleOwner, {
            adapter.addItemToBottom(it)
        })
    }

    private fun setupViews() {
        adapter = MessageAdapter(this)
        ui.apply {
            inputSend.addTextChangedListener { toggleSendImage() }
            imgPerson.apply {
                setImageByUrl(vm.user.image_url)
                setOnClickListener { navigateTo(MessageFragmentDirections.toPictureFragment(vm.user)) }
            }
            tvName.text = vm.user.name
            tvState.text = vm.user.state
            ui.toolbarUser.setupWithNavController(findNavController())
            imgSend.setOnClickListener { sendMessage() }
            listMessage.adapter = adapter
        }
        vm.fetchMessage()
    }

    private fun sendMessage() {
        vm.sendMessage(ui.inputSend.toText, "text")
        ui.inputSend.setText("")
    }

    private fun toggleSendImage() {
        ui.apply {
            if (inputSend.text.isNotEmpty()) imgSend.visible() else imgSend.invisible()
        }
    }

    override fun onMessageClick(message: Message) {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println(message)
    }

    override fun performViewBinding() = FragmentMessageBinding.inflate(layoutInflater)
}