package com.example.papameteo.domain.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papameteo.R
import com.example.papameteo.domain.model.FavViewModel
import com.example.papameteo.domain.model.FavViewModelState
import com.example.papameteo.domain.model.WeatherCityDomain
import org.koin.androidx.compose.getViewModel

@Composable
fun FavIcon(
    city: WeatherCityDomain,
    modifier: Modifier
) {
    val favViewModel = getViewModel<FavViewModel>()

    val state by remember(favViewModel) {
        favViewModel.isInFav(city)
    }.collectAsState(initial = FavViewModelState())

    IconButton(onClick = { favViewModel.addOrRemoveCity(city) }, modifier = modifier) {
        Icon(
            imageVector = if (state.isInFav) {
                Icons.Default.Favorite
            } else Icons.Filled.FavoriteBorder,
            contentDescription = stringResource(id = R.string.fav_icon_description)
        )
    }
}