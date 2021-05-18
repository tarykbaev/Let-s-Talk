package kg.turar.arykbaev.letstalk.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kg.turar.arykbaev.letstalk.data.firebase.AppChildEventListener
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.domain.NODE_MESSAGE
import kg.turar.arykbaev.letstalk.domain.NODE_USERS
import kg.turar.arykbaev.letstalk.domain.models.User
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val mAuth: FirebaseAuth,
    private val refDatabaseRoot: DatabaseReference
) {

    var users = MutableLiveData<User>()
    var chatUser = MutableLiveData<User>()
    private val appChildEventListener: AppChildEventListener
    val event: MutableLiveData<Event> = MutableLiveData()

    init {
        appChildEventListener = AppChildEventListener {
            val user = it.getValue(User::class.java) ?: User()
            if (user.id != currentUserId) {
                users.value = user
            }
        }
    }

    fun fetchUsers(countMessage: Int) {
        val refUser = refDatabaseRoot.child(NODE_USERS)
        refUser.removeEventListener(appChildEventListener)
        refUser.limitToLast(countMessage).addChildEventListener(appChildEventListener)
    }

    fun fetchChatUser(countMessage: Int) {
        val refMessages = refDatabaseRoot.child(NODE_MESSAGE).child(currentUserId)
        refMessages.addChildEventListener(AppChildEventListener { snapshot ->
            Log.d("fetchChatUser", snapshot.key.toString())
            snapshot.key?.let { key ->
                val refUser = refDatabaseRoot.child(NODE_USERS).child(key)
                refUser.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.getValue(User::class.java)?.let {
                            chatUser.value = it
                            Log.d("onDataChange", it.toString())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })
            }
        })
    }

    private val currentUserId: String
        get() = mAuth.currentUser?.uid.toString()
}