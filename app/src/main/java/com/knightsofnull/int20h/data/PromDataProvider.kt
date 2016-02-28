package com.knightsofnull.int20h.data

import android.content.Context
import com.knightsofnull.int20h.model.*
import com.knightsofnull.int20h.util.logD
import java.util.*

/**
 * Created by yarolegovich on 27.02.2016.
 */
class PromDataProvider(val storage: Storage = MockStorage()) : DataProvider {

    override fun getTypes() = storage.getTypes()

    override fun getCategories(typeId: Int): List<Category> {
        val result = ArrayList<Category>()
        result.add(Category.ALL)
        result.addAll(storage.getCategories().filter { it.typeId == typeId })
        return result
    }

    override fun getItems(typeId: Int, categoryId: Int, query: String): List<Item> {
        return (if (categoryId != Category.ALL.catId) {
            storage.getItems().filter { it.catId == categoryId }
        } else {
            storage.getCategories().filter { it.typeId == typeId }.flatMap { category ->
                storage.getItems().filter { item ->
                    item.catId == category.catId
                }
            }
        }).filter { it.name.contains(query) }
    }

    override fun getProducer(producerId: Int) = storage.getProducers().filter {
        it.id == producerId
    }.first()

    override fun getRequestsFor(catId: Int, query: String) = storage.getRequests().filter {
        it.catId == catId && it.name.contains(query)
    }

    override fun requestItem(item: Request) {
        storage.saveRequest(item)
    }

}