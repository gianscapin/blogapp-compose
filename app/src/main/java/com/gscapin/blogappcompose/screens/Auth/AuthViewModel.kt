package com.gscapin.blogappcompose.screens.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gscapin.blogappcompose.domain.auth.AuthRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepoImpl): ViewModel() {

    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading

    fun signUp(email: String, password: String, username: String, action: () -> Unit) = viewModelScope.launch {

        _loading.value = true
        repository.signUp(email, password, username)
        _loading.value = false
        action()
    }
}
