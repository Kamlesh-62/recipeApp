package com.example.recipestack

data class Category(
    val idCategory:String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data  class CategoriesResponse(
    val categories: List<Category>
)
