package com.example.recipestack

sealed class Screen( val route: String) {
    object RecipeScreen: Screen("recipescreen")
    object CategoryDetailScreen: Screen("detailscreen")
}