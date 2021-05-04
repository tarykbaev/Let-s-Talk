package kg.turar.arykbaev.letstalk.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.StorageReference
import kg.turar.arykbaev.letstalk.domain.*
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val mAuth: FirebaseAuth,
    private val refDatabaseRoot: DatabaseReference,
    private val refStorageRoot: StorageReference
) {


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
                Log.d("sendMessage", "Success: ")
                Log.d("sendMessage", refDialogUser)
                Log.d("sendMessage", refDialogReceiveUser)
            }
            .addOnFailureListener {
                Log.d("sendMessage", it.message.toString())
            }
    }

    private val currentUserId: String
        get() = mAuth.currentUser?.uid.toString()
}