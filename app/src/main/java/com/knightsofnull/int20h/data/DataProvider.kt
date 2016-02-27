package com.knightsofnull.int20h.data

import com.knightsofnull.int20h.model.*

/**
 * Created by yarolegovich on 27.02.2016.
 */
interface DataProvider {
    fun getTypes(): List<Type>
    fun getCategories(typeId: Int): List<Category>
    fun getItems(typeId: Int, categoryId: Int, query: String = ""): List<Item>
    fun getProducer(producerId: Int): Producer
    fun getRequestsFor(catId: Int, query: String = ""): List<Request>

    fun requestItem(item: Request)
}