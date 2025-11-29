package com.multiCounterApp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class CounterItem(
    val id: Long,
    val name: String,
    val value: Int
)

class CountersViewModel : ViewModel() {
    private val _counters = MutableStateFlow<List<CounterItem>>(emptyList())
    val counters: StateFlow<List<CounterItem>> = _counters

    private var nextIndex = 1L

    init {
        repeat(5) { addCounter() }
    }

    fun addCounter(startValue: Int = 0) {
        val id = System.currentTimeMillis() + Random.nextLong(0, 999)
        val name = "Counter_${nextIndex++}"
        _counters.update { old -> old + CounterItem(id = id, name = name, value = startValue) }
    }

    fun removeCounter(id: Long) {
        _counters.update { old -> old.filter { it.id != id } }
    }

    fun increment(id: Long) {
        _counters.update { list ->
            list.map { if (it.id == id) it.copy(value = it.value + 1) else it }
        }
    }

    fun decrement(id: Long) {
        _counters.update { list ->
            list.map { if (it.id == id) it.copy(value = it.value - 1) else it }
        }
    }

    fun setValue(id: Long, newValue: Int) {
        _counters.update { list ->
            list.map { if (it.id == id) it.copy(value = newValue) else it }
        }
    }
}
