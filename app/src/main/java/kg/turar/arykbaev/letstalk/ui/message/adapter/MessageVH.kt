package kg.turar.arykbaev.letstalk.ui.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import kg.turar.arykbaev.letstalk.databinding.ItemMessageBinding
import kg.turar.arykbaev.letstalk.domain.models.Message
import kg.turar.arykbaev.letstalk.extension.asTime
import kg.turar.arykbaev.letstalk.extension.gone
import kg.turar.arykbaev.letstalk.extension.visible
import kg.turar.arykbaev.letstalk.ui.base.CoreVH

class MessageVH(private val ui: ItemMessageBinding) : CoreVH<Message, ItemMessageBinding>(ui) {

    private lateinit var message: Message

    override fun onBind(item: Message) {
        message = item
        ui.apply {
            if (message.isCurrent) {
                clContainerStart.gone()
                clContainerEnd.visible()
                tvMessageEnd.text = item.text.parseAsHtml()
            } else {
                clContainerStart.visible()
                clContainerEnd.gone()
                tvMessage.text = item.text.parseAsHtml()
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: MessageAdapter.Listener): MessageVH {
            val view = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = MessageVH(view)
            holder.itemView.setOnClickListener { listener.onMessageClick(holder.message) }
            return holder
        }
    }
}