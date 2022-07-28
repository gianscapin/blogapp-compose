package com.gscapin.blogappcompose.screens.Auth

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gscapin.blogappcompose.components.InputField
import com.gscapin.blogappcompose.components.TopBarAccess

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarAccess(navController, "Login")
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Welcome back",
                    style = MaterialTheme.typography.h5,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = "Please log in to continue!",
                    style = MaterialTheme.typography.caption,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {
                    LoginForm() { email, pass ->
                        Log.d("email", email)
                        Log.d("password", pass)
                    }
                }
            }

        }
    }
}

@Composable
private fun LoginForm(datesDone: (String, String) -> Unit) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val passwordFocusRequest = FocusRequester.Default

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
            if (!valid) return@KeyboardActions

            datesDone(email.value.trim(), password.value.trim())
        }
    )
}

@Composable
fun PasswordInput(
    password: MutableState<String>,
    modifier: Modifier,
    label: String,
    passwordVisibility: MutableState<Boolean>,
    onAction: KeyboardActions,
    imeAction: ImeAction = ImeAction.Done
) {
    val visualTransformation =
        if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()

    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text(text = label) },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction
        )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close
    }
}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    mail: MutableState<String>,
    label: String = "Email",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = mail,
        label = label,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}


