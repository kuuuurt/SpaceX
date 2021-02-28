package com.kuuuurt.spacex.compose.presentation.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kuuuurt.spacex.compose.MainViewModelFactory
import com.kuuuurt.spacex.shared.domain.entities.Launch
import com.kuuuurt.spacex.compose.presentation.helpers.toStringDate
import com.kuuuurt.spacex.compose.presentation.theme.ChineseBlack
import com.kuuuurt.spacex.shared.presentation.LaunchesViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

val fakeLaunches = mutableListOf<Launch>().apply {
    repeat(50) {
        val index = it + 1
        add(
            Launch(
                name = "Launch $index",
                flightNumber = "Flight Number $index",
                date = 1583556631000,
                patch = "https://images2.imgbox.com/53/22/dh0XSLXO_o.png",
                images = listOf(
                    "https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg",
                    "https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg",
                    "https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg",
                    "https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg"
                )
            )
        )
    }
}

@Composable
fun Launches(launchesViewModel: LaunchesViewModel = viewModel(factory = MainViewModelFactory())) {
    val launches = launchesViewModel.launches.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(launches.value) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        // TODO: Navigate to Details
                    },
                backgroundColor = ChineseBlack
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.h4,
                        )
                        val patch = it.patch
                        if (patch != null) {
                            CoilImage(
                                data = patch,
                                contentDescription = it.name,
                                modifier = Modifier.size(40.dp),
                                fadeIn = true,
                                alignment = Alignment.Center
                            )
                        }
                    }

                    if (it.images.isNotEmpty()) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(240.dp),
                            contentPadding = PaddingValues(
                                top = 8.dp,
                                bottom = 8.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                        ) {
                            items(it.images) {
                                Card {
                                    CoilImage(
                                        data = it,
                                        contentDescription = it,
                                        fadeIn = true,
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = it.flightNumber,
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            text = it.date.toStringDate("MMMM dd, YYYY"),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}
