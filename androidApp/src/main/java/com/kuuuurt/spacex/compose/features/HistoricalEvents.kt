package com.kuuuurt.spacex.compose.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kuuuurt.spacex.compose.MainViewModelFactory
import com.kuuuurt.spacex.compose.presentation.helpers.toStringDate
import com.kuuuurt.spacex.shared.domain.entities.HistoricalEvent
import com.kuuuurt.spacex.shared.presentation.HistoricalEventsViewModel

val fakeHistoricalEvents = mutableListOf<HistoricalEvent>().apply {
    repeat(50) {
        val index = it + 1
        add(
            HistoricalEvent(
                title = "Title $index",
                date = 1583556631000,
                details = "This mission was the first crewed flight to launch from the United States since the end of the Space Shuttle program in 2011. It carried NASA astronauts Doug Hurley and Bob Behnken to the ISS."
            )
        )
    }
}

@Composable
fun HistoricalEvents(
    viewModel: HistoricalEventsViewModel = viewModel(
        factory = MainViewModelFactory()
    )
) {
    val historicalEvents = viewModel.historicalEvents.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(historicalEvents.value) {
            Column(
                modifier = Modifier
                    .clickable {
                        // TODO: Navigate to Details
                    }
            ) {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = it.date.toStringDate("MMMM dd, YYYY"),
                    style = MaterialTheme.typography.caption,
                )

                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = it.details,
                    style = MaterialTheme.typography.body1,
                )
                Spacer(modifier = Modifier.size(16.dp))

                Divider(color = Color.White, thickness = 2.dp)
            }
        }
    }
}