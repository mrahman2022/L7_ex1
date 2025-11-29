package com.multiCounterApp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.multiCounterApp.viewmodel.CountersViewModel
import com.multiCounterApp.viewmodel.CounterItem
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState

// Import the required annotation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Add the OptIn annotation here to resolve the experimental API usage warning
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterListScreen(viewModel: CountersViewModel, modifier: Modifier = Modifier) {
    val counters by viewModel.counters.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Multi Counter") }, actions = {
                IconButton(onClick = { viewModel.addCounter() }) { Text("+ Add") }
            })
        }
    ) { padding ->
        Column(modifier = modifier.padding(padding).fillMaxSize()) {
            if (counters.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No counters. Tap + Add to create one.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(counters, key = { it.id }) { item ->
                        CounterRow(
                            counter = item,
                            onIncrement = { viewModel.increment(item.id) },
                            onDecrement = { viewModel.decrement(item.id) },
                            onSetValue = { newVal -> viewModel.setValue(item.id, newVal) },
                            onRemove = { viewModel.removeCounter(item.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CounterRow(
    counter: CounterItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onSetValue: (Int) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors()
    ) {
        Row(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = counter.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                var text by remember { mutableStateOf(counter.value.toString()) }
                OutlinedTextField(
                    value = text,
                    onValueChange = { input ->
                        if (input.isEmpty() || input.matches(Regex("-?\\d*"))) {
                            text = input
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    label = { Text("Value") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Button(onClick = {
                        val v = text.toIntOrNull()
                        if (v != null) onSetValue(v)
                        else { text = counter.value.toString() }
                    }) { Text("Set") }
                    Spacer(Modifier.width(8.dp))
                    OutlinedButton(onClick = onRemove) { Text("Remove") }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 12.dp)) {
                IconButton(onClick = onIncrement) { Text("+") }
                Text(counter.value.toString(), style = MaterialTheme.typography.headlineSmall)
                IconButton(onClick = onDecrement) { Text("-") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCounterRow() {
    val sample = CounterItem(id = 1L, name = "Counter_1", value = 3)
    CounterRow(
        counter = sample,
        onIncrement = {},
        onDecrement = {},
        onSetValue = {},
        onRemove = {}
    )
}