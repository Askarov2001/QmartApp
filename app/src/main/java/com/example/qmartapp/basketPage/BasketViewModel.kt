package com.example.qmartapp.basketPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qmartapp.base.database.BasketEntity
import com.example.qmartapp.base.database.FavouritesEntity
import com.example.qmartapp.productsPage.adapter.ProductsDisplayItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class BasketViewModel(private val repository: BasketRepository) : ViewModel(), KoinComponent {
    private val _items = MutableStateFlow(ArrayList<BasketEntity>())
    val items: StateFlow<List<BasketEntity>> = _items

    fun getAll() {
        viewModelScope.launch {
            var result = ArrayList<BasketEntity>()
            withContext(Dispatchers.IO) {
                result = repository.getAll() as ArrayList<BasketEntity>
            }
            withContext(Dispatchers.Main) {
                _items.value = result
            }
        }
    }

    fun increaseCount(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.increaseCount(id)
            }
        }
    }

    fun decreaseCount(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.decreaseCount(id)
            }
        }
    }

    fun addToBasket(item: ProductsDisplayItem, count: Int = 1) {
        viewModelScope.launch {
            var result = false
            withContext(Dispatchers.IO) {
                result = repository.add(
                    BasketEntity(
                        item.id,
                        item.title,
                        item.image,
                        count,
                        item.price,
                        item.sellerId
                    )
                )
            }
            withContext(Dispatchers.Main) {

            }
        }
    }

    fun addToBasket(item: FavouritesEntity) {
        viewModelScope.launch {
            var result = false
            withContext(Dispatchers.IO) {
                result = repository.add(
                    BasketEntity(
                        item.id,
                        item.name,
                        item.image,
                        1,
                        item.price,
                        item.sellerId
                    )
                )
                if (!result) {
                    increaseCount(item.id)
                }
            }
            withContext(Dispatchers.Main) {

            }
        }
    }

    fun deleteItem(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteItem(id)
                getAll()
            }
        }
    }

    fun updateItem(id: String, count: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateCount(id, count)
                getAll()
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                (repository.getAll() as ArrayList<BasketEntity>).also {
                    it.forEach {
                        repository.deleteItem(it.id)
                    }
                }
            }
        }
    }
}