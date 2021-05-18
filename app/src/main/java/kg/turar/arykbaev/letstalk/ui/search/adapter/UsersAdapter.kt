package kg.turar.arykbaev.letstalk.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.turar.arykbaev.letstalk.domain.models.User

class UsersAdapter(private val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<User> = ArrayList()

    fun clearItems() {
        items.clear()
    }

    fun addItem(user: User) {
        val position = items.indexOfFirst { it.id == user.id }
        if (position > -1) {
            items[position] = user
            notifyItemChanged(position)
        } else {
            items.add(user)
            notifyItemInserted(items.size)
        }
    }

    fun updateItem(list: List<User>) {
        items.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        return UsersVH.create(parent, listener)
    }

    interface Listener {
        fun onUserClick(user: User)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UsersVH -> holder.onBind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size
}