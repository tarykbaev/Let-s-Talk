package kg.turar.arykbaev.letstalk.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kg.turar.arykbaev.letstalk.domain.*
import javax.inject.Inject

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AuthAppRepository @Inject constructor() {

    private val mAuth: FirebaseAuth
    private val refDatabaseRoot: DatabaseReference
    private val event: MutableLiveData<Event> = MutableLiveData()
    private var isNewEmail = false

    init {
        mAuth = FirebaseAuth.getInstance()
        refDatabaseRoot = FirebaseDatabase.getInstance().reference
    }

    fun getEvent() = event

    fun register(user: User) {
        event.value = Event.Loading()
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                val uid = mAuth.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_EMAIL] = user.email
                dateMap[CHILD_USERNAME] = uid

                refDatabaseRoot.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnSuccessListener {
                        sendVerification()
                    }
                    .addOnFailureListener {
                        event.value = Event.Notification(it.message.toString())
                    }
            }
            .addOnFailureListener {
                event.value = Event.Notification(it.message.toString())
            }
    }

    private fun sendVerification() {
        mAuth.currentUser.sendEmailVerification()
            .addOnSuccessListener {
                event.value = Event.Success()
            }
            .addOnFailureListener {
                event.value = Event.Notification(it.message.toString())
            }
    }

    fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                when (mAuth.currentUser?.isEmailVerified) {
                    true -> event.value = Event.Success()
                    else -> event.value = Event.Notification("Please, Verify your email")
                }
            }
            .addOnFailureListener {
                event.value = Event.Notification(it.message.toString())
            }
    }

    fun logout() {
        mAuth.signOut()
    }

    fun checkAuthentication() {
        when (mAuth.currentUser != null && mAuth.currentUser?.isEmailVerified == true) {
            true -> event.value = Event.Notification("Welcome!")
            else -> event.value = Event.Success()
        }
    }

    fun checkEmail(email: String) {
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            when(it.result?.signInMethods?.isEmpty()) {
                true -> event.value = Event.Success()
                false -> event.value = Event.Notification("This email address already exists")
            }
        }
    }
}