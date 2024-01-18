package com.example.papameteo.domain.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papameteo.domain.model.CityViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun WeatherMap(modifier: Modifier, viewModel: CityViewModel) {
    val ville = LatLng(viewModel.lat, viewModel.lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ville, 10f)
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = ville),
            title = viewModel.label,
            snippet = "Population: " + viewModel.population
        )
    }
}