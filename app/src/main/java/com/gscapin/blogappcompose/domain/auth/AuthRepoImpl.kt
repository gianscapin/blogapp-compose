package com.gscapin.blogappcompose.domain.auth

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser
import com.gscapin.blogappcompose.data.remote.auth.AuthDataSource
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(private val dataSource: AuthDataSource): AuthRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? = dataSource.signUp(email, password, username)

    override suspend fun updateProfile(imageBitmap: Bitmap, username: String) = dataSource.updateProfile(imageBitmap, username)
}