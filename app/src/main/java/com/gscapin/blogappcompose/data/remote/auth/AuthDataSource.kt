package com.gscapin.blogappcompose.data.remote.auth

import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.gscapin.blogappcompose.data.model.User
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class AuthDataSource @Inject constructor() {

    suspend fun signIn(email: String, password: String): FirebaseUser?{
        val authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResult.user
    }

    suspend fun signUp(email: String, password: String, username: String): FirebaseUser? {
        val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()

        authResult.user?.let {
            FirebaseFirestore.getInstance().collection("users").document(it.uid).set(
                User(username = username, email = email, userPhotoUrl = "")
            ).await()
        }
        return authResult.user
    }

    suspend fun updateProfile(imageBitmap: Bitmap, username: String){
        val user = FirebaseAuth.getInstance().currentUser
        val imageRef = FirebaseStorage.getInstance().reference.child("${user?.uid}/profile_picture")
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val downloadUrl =  imageRef.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()

        val profileUpdates = userProfileChangeRequest {
            displayName = username
            photoUri = Uri.parse(downloadUrl)
        }

        user?.updateProfile(profileUpdates)?.await()
    }
}