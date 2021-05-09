package kg.turar.arykbaev.letstalk.data.repository

import kg.turar.arykbaev.letstalk.domain.models.Message
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.StorageReference
import kg.turar.arykbaev.letstalk.data.firebase.AppChildEventListener
import kg.turar.arykbaev.letstalk.domain.*
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val mAuth: FirebaseAuth,
    private val refDatabaseRoot: DatabaseReference,
    private val refStorageRoot: StorageReference
) {

    var message = MutableLiveData<Message>()
    private val appChildEventListener: AppChildEventListener

    init {
        appChildEventListener = AppChildEventListener {
            val messages = it.getValue(Message::class.java) ?: Message()
            message.value = messages.apply { isCurrent = from == currentUserId }
        }
    }


    fun sendMessage(message: String, receiveUserId: String, type: String) {
        val refDialogUser = "$NODE_MESSAGE/$currentUserId/$receiveUserId"
        val refDialogReceiveUser = "$NODE_MESSAGE/$receiveUserId/$currentUserId"
        val messageKey = refDatabaseRoot.child(refDialogUser).push().key

        val mapMessage = hashMapOf<String, Any>()
        mapMessage[CHILD_FROM_MESSAGE] = currentUserId
        mapMessage[CHILD_TYPE] = type
        mapMessage[CHILD_TEXT] = message
        mapMessage[CHILD_TIME] = ServerValue.TIMESTAMP

        val mapDialog = hashMapOf<String, Any>()
        mapDialog["$refDialogUser/$messageKey"] = mapMessage
        mapDialog["$refDialogReceiveUser/$messageKey"] = mapMessage

        refDatabaseRoot.updateChildren(mapDialog)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
    }

    fun fetchMessage(receiveUserId: String, countMessage: Int) {
        val refMessages = refDatabaseRoot.child(NODE_MESSAGE).child(currentUserId).child(receiveUserId)
        refMessages.removeEventListener(appChildEventListener)
        refMessages.limitToLast(countMessage).addChildEventListener(appChildEventListener)
    }

    private val currentUserId: String
        get() = mAuth.currentUser?.uid.toString()
}