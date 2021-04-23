package kg.turar.arykbaev.letstalk.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import javax.inject.Inject

class AuthAppRepository @Inject constructor() {

    private val mAuth: FirebaseAuth
    private val refDatabaseRoot: DatabaseReference
    private val event: MutableLiveData<Event> = MutableLiveData()
    private var isNewEmail = MutableLiveData<Boolean>()

    init {
        mAuth = FirebaseAuth.getInstance()
        refDatabaseRoot = FirebaseDatabase.getInstance().reference
    }

    fun getEvent() = event

    fun login(email: String, password: String) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    when(mAuth.currentUser?.isEmailVerified) {
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
        when(mAuth.currentUser != null && mAuth.currentUser?.isEmailVerified == true) {
            true -> event.value = Event.Notification("Welcome!")
            else -> event.value = Event.Success()
        }
    }

    fun isNewEmail(email: String): LiveData<Boolean> {
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            isNewEmail.value = it.result?.signInMethods?.isEmpty() == true
        }
        return isNewEmail
    }
}