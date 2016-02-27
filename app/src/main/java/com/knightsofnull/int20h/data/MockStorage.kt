package com.knightsofnull.int20h.data

import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Item
import com.knightsofnull.int20h.model.Producer
import com.knightsofnull.int20h.model.Type

/**
 * Created by yarolegovich on 27.02.2016.
 */
class MockStorage : Storage {

    override fun getTypes() = listOf(
            Type(1, "Industrial goods"),
            Type(2, "Consumer goods"),
            Type(3, "Services")
    )

    override fun getCategories() = listOf(
            Category(1, 1, "Building"),
            Category(2, 1, "Medicine"),
            Category(3, 1, "Security and protection"),
            Category(4, 1, "Metal and rubber"),
            Category(5, 1, "Production tools"),
            Category(6, 2, "Clothes and accessories"),
            Category(7, 2, "For kids"),
            Category(8, 2, "Auto and moto"),
            Category(9, 2, "Sport"),
            Category(10, 2, "Health"),
            Category(11, 3, "Design"),
            Category(12, 3, "Ads and PR"),
            Category(13, 3, "IT services"),
            Category(14, 3, "Industrial services"),
            Category(15, 3, "Jurisdiction")
    )

    override fun getItems() = listOf(
            Item(1, "Bricks", "100 uah", 1, 1, true, 214),
            Item(2, "Clay", "30 uah", 1, 1, true, 123),
            Item(3, "Screws", "20 uah", 1, 1, true, 312),
            Item(4, "Hematogen", "12 uah", 2, 2, true, 1299),
            Item(5, "Lacoste T-shirt", "400 uah", 6, 3, true, 502),
            Item(6, "Gabano shoes", "900 uah", 6, 3, true, 1300),
            Item(7, "Boxing gloves", "430 uah", 9, 3, true, 514),
            Item(8, "Android app development", "15$/hour", 13, 4, true, 5020),
            Item(9, "UI/UX design", "20$/hour", 11, 5, true, 3200),
            Item(10, "App reviews", "10$/hour", 12, 6, true, 9999)
    )

    override fun getProducers() = listOf(
            Producer(1, "UkrBuild Co", "+38092394912", "72%"),
            Producer(2, "Blagodiya", "+38064392902", "60%"),
            Producer(3, "ChinaClothes", "+3809328499", "33%"),
            Producer(4, "Yaroslav Shevchuk", "+380934739866", "98%"),
            Producer(5, "Yurkiv T", "+380935748778", "78%"),
            Producer(6, "Sikun Pavel", "+3809202192", "50%")

    )

    override fun putItem(item: Item) {

    }

}