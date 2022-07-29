package com.gscapin.blogappcompose.screens.welcome

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gscapin.blogappcompose.Navigation.BlogScreens
import com.gscapin.blogappcompose.components.AdjustSystemBarColor
import com.gscapin.blogappcompose.components.CreateAccountButton
import com.gscapin.blogappcompose.components.LoginButton

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdjustSystemBarColor()
            BlogLogo()
            WelcomeText()
            Spacer(modifier = Modifier.height(10.dp))
            CreateAccountButton() {
                navController.navigate(BlogScreens.CreateAccountScreen.name)
            }
            LoginButton(){
                navController.navigate(BlogScreens.LoginScreen.name)
            }
        }
    }
}



@Composable
fun WelcomeText() {
    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome!", style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = "Choose some button.", style = MaterialTheme.typography.body1,
            fontFamily = FontFamily.SansSerif
        )

    }

}

@Composable
fun BlogLogo() {
    Text(
        text = "BlogApp", style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(10.dp),
        fontFamily = FontFamily.SansSerif
    )
}
