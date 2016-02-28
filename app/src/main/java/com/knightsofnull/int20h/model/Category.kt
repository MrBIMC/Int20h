package com.knightsofnull.int20h.model

/**
 * Created by yarolegovich on 27.02.2016.
 */
data class Category(
        val catId: Int,
        val typeId: Int,
        val name: String
) {
        companion object {
                val ALL = Category(0, 0, "Всё")
        }
}