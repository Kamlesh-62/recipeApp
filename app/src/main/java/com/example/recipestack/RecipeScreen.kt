package com.example.recipestack

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import android.util.Log


@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState: MainViewModel.RecipeState,
    navigateToDetail: (Category) -> Unit
) {
    Log.d("MY_LOG_TAG", "This is Recipe Screen file.")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier.align(Alignment.Center)
                )
            }

            viewState.error != null -> {
                Text("Error Occurred")
            }

            else -> {
                // here we will display all the categories
                CategoryScreen(
                    categories = viewState.list,
                    navigateToDetail
                )
            }
        }

    }
}

@Composable
fun CategoryScreen(
    categories: List<Category>,
    navigateToDetail: (Category) -> Unit
) {
    LazyVerticalGrid(
        GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
    ) {
        items(categories) { cat ->
            EachCategoryItem(
                cat = cat,
                navigateToDetail
            )
        }
    }
}

@Composable
fun EachCategoryItem(
    cat: Category,
    navigateToDetail: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                navigateToDetail(cat)
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(cat.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = cat.strCategory, color = Color.Black, style = TextStyle(
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(top = 4.dp)
        )
    }
}