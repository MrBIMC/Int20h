package com.knightsofnull.int20h.data

import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Producer
import com.knightsofnull.int20h.model.Type

/**
 * Created by yarolegovich on 27.02.2016.
 */
class PromDataProvider(val storage: Storage = MockStorage()) : DataProvider {

    override fun getTypes() = storage.getTypes()

    override fun getCategories(typeId: Int) = storage.getCategories().filter { it.typeId == typeId }

    override fun getItems(categoryId: Int) = storage.getItems().filter { it.catId == categoryId }

    override fun getProducer(producerId: Int) = storage.getProducers().filter {
        it.id == producerId
    }.first()

    override fun requestItem(item: Item) {
        val requested = storage.getItems().find { it.id == item.id }
        val hasPreviouslyRequested = requested != null
        if (hasPreviouslyRequested) {
            storage.putItem(Item(item.id,
                    item.name, item.price,
                    item.catId, item.producerId,
                    false, item.itemRating + 1
            ))
        } else {
            storage.putItem(item)
        }
    }

}