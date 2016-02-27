package com.knightsofnull.int20h.data

import android.content.Context
import com.knightsofnull.int20h.model.*
import com.knightsofnull.int20h.util.logD

/**
 * Created by yarolegovich on 27.02.2016.
 */
class PromDataProvider(val storage: Storage = MockStorage()) : DataProvider {

    override fun getTypes() = storage.getTypes()

    override fun getCategories(typeId: Int) = storage.getCategories().filter { it.typeId == typeId }

    override fun getItems(typeId: Int, categoryId: Int, query: String): List<Item> {
        val list = if (categoryId != Category.ALL.catId) {
            storage.getItems().filter { it.catId == categoryId }
        } else {
            storage.getCategories().filter { it.typeId == typeId }.flatMap { category ->
                storage.getItems().filter { item ->
                    item.catId == category.catId
                }
            }
        }
        return if (query.isEmpty()) list else list.filter { it.name.contains(query) }
    }

    override fun getProducer(producerId: Int) = storage.getProducers().filter {
        it.id == producerId
    }.first()

//    override fun getRequestsFor(catId: Int, query: String): List<Request> {
//        val requests = storage.getRequests().filter { it.catId == catId }
//        return if (query.isEmpty()) requests else requests.filter { it.name.contains(query) }
//    }

    override fun getRequestsFor(catId: Int, query: String) = listOf(
            Request(1, "Add animations", "Add animations to this damn app please", 42),
            Request(2, "Add FlowLayout", "Pavel, add flow layout please", 23)
    )

    override fun requestItem(item: Request) {
        storage.saveRequest(item)
    }

}