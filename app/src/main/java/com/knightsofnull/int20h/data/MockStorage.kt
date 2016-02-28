package com.knightsofnull.int20h.data

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.knightsofnull.int20h.App
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.model.*
import org.json.JSONArray
import java.util.*

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
            Category(1, 1, "Строительство"),
            Category(3, 1, "Безопасность"),
            Category(2, 1, "Медицина"),
            Category(4, 1, "Метал и резина"),
            Category(5, 1, "Инструменты"),
            Category(6, 2, "Одежда"),
            Category(7, 2, "Для детей"),
            Category(8, 2, "Авто и мото"),
            Category(9, 2, "Спорт"),
            Category(10, 2, "Здоровье"),
            Category(11, 3, "Дизайн"),
            Category(12, 3, "PR и реклама"),
            Category(13, 3, "IT услуги"),
            Category(14, 3, "Промышленные услуги"),
            Category(15, 3, "Юриспунденция")
    )

    override fun getItems() = listOf(
            Item(1, "Кирпичи", "100 uah", 1, 1, 214, R.drawable.bricks),
            Item(2, "Глина", "30 uah", 1, 1, 123, R.drawable.clay),
            Item(3, "Шурупы", "20 uah", 1, 1, 312, R.drawable.screws),
            Item(4, "Гематоген", "12 uah", 2, 2, 1299, R.drawable.hematogen),
            Item(5, "Lacoste футболки", "400 uah", 6, 3, 502, R.drawable.shirts),
            Item(6, "Gabano кросовки", "900 uah", 6, 3, 1300, R.drawable.shoes),
            Item(7, "Боксерские перчатки", "430 uah", 9, 3, 514, R.drawable.gloves),
            Item(8, "Android OS приложения", "15$/hour", 13, 4, 5020, R.drawable.it),
            Item(9, "UI/UX дизайн", "20$/hour", 11, 5, 3200, R.drawable.ux),
            Item(10, "Обзоры приложений", "10$/hour", 12, 6, 9999, R.drawable.pr)
    )

    override fun getProducers() = listOf(
            Producer(1, "UkrBuild Co", "+38092394912", "72%"),
            Producer(2, "Blagodiya", "+38064392902", "60%"),
            Producer(3, "ChinaClothes", "+3809328499", "33%"),
            Producer(4, "Yaroslav Shevchuk", "+380934739866", "98%"),
            Producer(5, "Yurkiv T", "+380935748778", "78%"),
            Producer(6, "Sikun Pavel", "+3809202192", "50%")

    )

    override fun getRequests(): List<Request> {
        val context = App.instance.applicationContext
        val storage = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE)
        val requestsJson = storage.getString(REQUESTS, "")
        return if (!requestsJson.isEmpty()) {
            val token = object: TypeToken<List<Request>>() {}
            Gson().fromJson(requestsJson, token.type)
        } else listOf()
    }

    override fun saveRequest(item: Request) {
        val context = App.instance.applicationContext
        val storage = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE)
        val requestsJson = storage.getString(REQUESTS, "")
        val requests = if (!requestsJson.isEmpty()) {
            val token = object: TypeToken<List<Request>>() {}
            Gson().fromJson(requestsJson, token.type)
        } else ArrayList<Request>()

        if (requests.contains(item)) {
            requests.remove(item)
            requests.add(Request.incrementVotes(item))
        } else {
            requests.add(item)
        }

        storage.edit().putString(STORAGE, Gson().toJson(requests))
    }

    companion object {
        private const val STORAGE = "requests"
        private const val REQUESTS = "key_requests"
    }

}