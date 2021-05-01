package kg.turar.arykbaev.letstalk.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.turar.arykbaev.letstalk.databinding.UsersItemBinding
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.extension.setImageByUrl
import kg.turar.arykbaev.letstalk.ui.base.CoreVH

class UsersVH(private val ui: UsersItemBinding) : CoreVH<User, UsersItemBinding>(ui) {

    private lateinit var user: User

    override fun onBind(item: User) {
        user = item
        ui.tvName.text = user.name
        ui.imgProfile.setImageByUrl(user.image_url)
    }

    companion object {
        fun create(parent: ViewGroup, listener: UsersAdapter.Listener): UsersVH {
            val view = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = UsersVH(view)
            holder.itemView.setOnClickListener { listener.onUserClick(holder.user) }
            return holder
        }
    }
}