package com.example.papameteo.domain.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papameteo.R
import com.example.papameteo.domain.components.Screen
import com.example.papameteo.domain.components.WeatherDetailsItem
import com.example.papameteo.domain.components.WeatherMap
import com.example.papameteo.domain.components.WeatherSearchBar
import com.example.papameteo.domain.model.CityViewModel
import com.example.papameteo.domain.model.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchScreen(
    navHostController: NavHostController
) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val weatherViewModel = getViewModel<WeatherViewModel>()

    val state by weatherViewModel.viewState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var locationSearching by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text("") },
                actions = {
                    WeatherSearchBar(
                        searchText = text,
                        placeholderText = stringResource(id = R.string.search_placeholder),
                        onSearchTextChanged = { it ->
                            text = it
                            weatherViewModel.cityChanged(text)
                        },
                        onClearClick = {
                            text = ""
                            weatherViewModel.cityChanged(text)
                        },
                        onLocateSearching = {
                            locationSearching = it
                        },
                        onLocateChange = {
                            weatherViewModel.locationChanged(it)
                        }
                    )
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        ) {
            if (state.isLoading || locationSearching) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = stringResource(
                            id =  if (state.isLoading) {
                                R.string.search_processing_label
                            } else {
                                R.string.localisation_processing_label
                            }
                        )
                    )
                }
            } else if (state.city != null && state.first != null) {
                val city = state.city!!
                val item = state.first!!

                WeatherMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(10.dp)
                        .weight(1f),
                    viewModel = CityViewModel(city = city)
                )

                WeatherDetailsItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .weight(2f),
                    city = city,
                    item = item
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(onClick = {
                        navHostController.navigate(route = "${Screen.WeatherList.route}/${city.name}")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = stringResource(id = R.string.icn_go_to_details)
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_weather_question
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.entry_label),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}