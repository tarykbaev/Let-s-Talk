package kg.turar.arykbaev.letstalk.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val mAuth: FirebaseAuth,
    private val refDatabaseRoot: DatabaseReference,
    private val refStorageRoot: StorageReference
) {


    private val currentUserId: String
        get() = mAuth.currentUser?.uid.toString()
}