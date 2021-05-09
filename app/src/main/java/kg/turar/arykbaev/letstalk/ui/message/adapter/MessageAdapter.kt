package kg.turar.arykbaev.letstalk.ui.message.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.turar.arykbaev.letstalk.domain.models.Message

class MessageAdapter(private val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Message> = ArrayList()

    fun addItemToBottom(message: Message) {
        if (!items.contains(message)) {
            items.add(message)
            notifyItemInserted(items.size)
        }
    }

    fun addItemToTop(message: Message) {
        if (!items.contains(message)) {
            items.add(message)
            items.sortBy { it.timeStamp }
            notifyItemInserted(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MessageVH.create(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MessageVH -> holder.onBind(items[position])
        }
    }

    override fun getItemCount() = items.size

    interface Listener {
        fun onMessageClick(message: Message)
    }
}