package com.example.papameteo.domain.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papameteo.R
import com.example.papameteo.domain.components.WeatherList
import com.example.papameteo.domain.model.WeatherDetailsViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsListScreen(
    navHostController: NavHostController,
    cityName: String
) {
    val weatherViewModel = getViewModel<WeatherDetailsViewModel>()
    val state by weatherViewModel.viewState.collectAsState()

    LaunchedEffect(true) {
        weatherViewModel.cityChanged(cityName)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.list_title_label, cityName ?: ""))
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            modifier = Modifier,
                            contentDescription = stringResource(id = R.string.icn_search_back_content_description)
                        )
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            if (state.isLoading) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                    CircularProgressIndicator()
                }
            } else if (state.items.isNotEmpty()) {
                WeatherList(weatherItems = state.items)
            }
        }
    }
}