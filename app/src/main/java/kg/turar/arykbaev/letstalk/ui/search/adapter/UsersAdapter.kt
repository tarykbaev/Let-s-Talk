package kg.turar.arykbaev.letstalk.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import kg.turar.arykbaev.letstalk.domain.models.User

class UsersAdapter(
    private val options: FirebaseRecyclerOptions<User>,
    private val listener: Listener
) : FirebaseRecyclerAdapter<User, RecyclerView.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        return UsersVH.create(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, model: User) {
        when(holder) {
            is UsersVH -> holder.onBind(model)
        }
    }

    interface Listener {
        fun onUserClick(user: User)
    }
}