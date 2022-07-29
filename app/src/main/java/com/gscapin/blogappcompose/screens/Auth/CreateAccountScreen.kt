package com.gscapin.blogappcompose.screens.Auth

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gscapin.blogappcompose.components.*

@Composable
fun CreateAccountScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarAccess(navController, "Create account")
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sign up!",
                    style = MaterialTheme.typography.h5,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = "Create an account to access a great community and find out what's new.",
                    style = MaterialTheme.typography.caption,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 15.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {
                    CreateAccountForm() { email, pass, name ->
                        Log.d("email", email)
                        Log.d("password", pass)
                        Log.d("name", name)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateAccountForm(createAccountAction: (String, String, String) -> Unit) {

    val name = rememberSaveable {
        mutableStateOf("")
    }
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }

    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    val confirmPassword = rememberSaveable {
        mutableStateOf("")
    }

    val validPassword = remember(password.value, confirmPassword.value) {
        password.value.trim() == confirmPassword.value.trim() && password.value.trim().isNotEmpty() && confirmPassword.value.trim().isNotEmpty()
    }


    val passwordFocusRequest = FocusRequester.Default

    NameInput(
        name = name,
        onAction = KeyboardActions { passwordFocusRequest.requestFocus() })
    Spacer(modifier = Modifier.height(30.dp))
    EmailInput(
        mail = email,
        onAction = KeyboardActions { passwordFocusRequest.requestFocus() })
    Spacer(modifier = Modifier.height(30.dp))
    PasswordInput(
        password = password,
        modifier = Modifier.focusRequester(passwordFocusRequest),
        label = "Password",
        passwordVisibility = passwordVisibility,
        onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        },
        imeAction = ImeAction.Next
    )
    Spacer(modifier = Modifier.height(30.dp))
    PasswordInput(
        password = confirmPassword,
        modifier = Modifier.focusRequester(passwordFocusRequest),
        label = "Confirm password",
        passwordVisibility = passwordVisibility,
        onAction = KeyboardActions {
            if (!validPassword) return@KeyboardActions

            createAccountAction(email.value.trim(), password.value.trim(), name.value.trim())
        }
    )
    SubmitCreateAccount(enabled = validPassword){
        createAccountAction(email.value.trim(), password.value.trim(), name.value.trim())
    }
}
