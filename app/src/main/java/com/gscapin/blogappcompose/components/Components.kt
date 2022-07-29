package com.gscapin.blogappcompose.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AdjustSystemBarColor(color: Color = Color.White) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color
        )
    }
}

@Composable
fun TopBarAccess(navController: NavController, title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}

@Composable
fun InputField(
    modifier: Modifier,
    valueState: MutableState<String>,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onAction: KeyboardActions,
    isSingleLine: Boolean = true,
    iconInput: ImageVector = Icons.Default.Email
) {
    TextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        singleLine = isSingleLine,
        label = { Text(text = label)},
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        //keyboardActions = onAction,
        leadingIcon = {
            Icon(imageVector = iconInput, contentDescription = "icon input")
        }
    )
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(50.dp)
    ) {
        Text(text = "LOGIN", fontFamily = FontFamily.SansSerif)
    }
}

@Composable
fun LoginAccountButton(onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(50.dp)
    ) {
        Text(text = "LOG IN", fontFamily = FontFamily.SansSerif)
    }
}


@Composable
fun CreateAccountButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(50.dp)
    ) {
        Text(text = "CREATE AN ACCOUNT", fontFamily = FontFamily.SansSerif)
    }
}

@Composable
fun SubmitCreateAccount(enabled:Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(50.dp),
        enabled = enabled
    ) {
        Text(text = "CREATE AN ACCOUNT", fontFamily = FontFamily.SansSerif)
    }
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
        keyboardActions = onAction,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "password icon")
        }
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

@Composable
fun NameInput(
    modifier: Modifier = Modifier,
    name: MutableState<String>,
    label: String = "Full name",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = name,
        label = label,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction,
        iconInput = Icons.Default.Person
    )
}