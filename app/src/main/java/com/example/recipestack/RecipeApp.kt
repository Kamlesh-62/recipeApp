package com.example.recipestack

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import android.util.Log

@Composable
fun RecipeApp(
    navController: NavHostController
) {
    //logcat logs
    Log.d("MY_LOG_TAG", "This is RecipeApp file")

    val recipeViewMode: MainViewModel = viewModel()
    val viewState by recipeViewMode.categoriesState


    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(
                viewState = viewState,
                navigateToDetail = { category ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", category)
                    navController.navigate(Screen.CategoryDetailScreen.route)
                })
        }

        composable(route = Screen.CategoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                    ?: Category(
                        idCategory = "",
                        strCategory = "",
                        strCategoryThumb = "",
                        strCategoryDescription = ""
                    )
            CategoryDetailScreen(
                category = category
            )
        }
    }
}
