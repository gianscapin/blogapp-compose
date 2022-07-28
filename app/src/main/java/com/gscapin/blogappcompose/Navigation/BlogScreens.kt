package com.gscapin.blogappcompose.Navigation

enum class BlogScreens {
    WelcomeScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    NewPostScreen,
    ProfileScreen,
    ProfilePhotoScreen;

    companion object{
        fun fromRoute(route: String): BlogScreens =
            when(route?.substringBefore("/")){
                WelcomeScreen.name -> WelcomeScreen
                LoginScreen.name -> LoginScreen
                CreateAccountScreen.name -> CreateAccountScreen
                HomeScreen.name -> HomeScreen
                NewPostScreen.name -> NewPostScreen
                ProfileScreen.name -> ProfileScreen
                ProfilePhotoScreen.name -> ProfilePhotoScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}