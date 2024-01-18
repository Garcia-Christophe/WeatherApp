package com.example.papameteo.domain.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.papameteo.R
import com.example.papameteo.domain.model.CoordinateDomain
import com.example.papameteo.domain.model.WeatherCityDomain
import com.example.papameteo.domain.model.WeatherInfoDomain
import com.example.papameteo.domain.model.WeatherItemDomain
import java.time.LocalDateTime

@Composable
fun WeatherDetailsItem(modifier: Modifier, city: WeatherCityDomain, item: WeatherItemDomain) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.weight(2f),
                text = stringResource(id = R.string.details_city_label, city.name)
            )
            
            FavIcon(city = city, modifier = Modifier)
        }

        Text(
            text = stringResource(id = R.string.details_hour_label, item.hour ?: "")
        )

        Image(
            painter = painterResource(
                id = when (item.infos.humidity) {
                    in 0.0..50.0 -> R.drawable.ic_sun
                    in 50.0..80.0 -> R.drawable.ic_cloud
                    in 80.0..90.0 -> R.drawable.ic_cloud_gray
                    else -> R.drawable.ic_rain
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(100.dp)
        )

        Text(
            text = stringResource(
                id = R.string.details_infos_label,
                item.infos.temp,
                item.infos.humidity
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun WeatherDetailsItemPreview() {
    WeatherDetailsItem(
        modifier = Modifier,
        city = WeatherCityDomain(
            name = "Brest",
            coord = CoordinateDomain(),
            population = 1000000,
        ),
        item = WeatherItemDomain(
            date = LocalDateTime.now(),
            image = "",
            infos = WeatherInfoDomain(
                temp = 10.0,
                humidity = 50.0,
                pressure = 0.0
            ),
        ).apply {
            day = "21"
            hour = "16"
        }
    )
}