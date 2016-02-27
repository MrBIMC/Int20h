package com.knightsofnull.int20h.model

/**
 * Created by yarolegovich on 27.02.2016.
 */
data class Request(
        val catId: Int,
        val name: String,
        val description: String,
        val votes: Int) : ShopModel {
    companion object {
        fun incrementVotes(request: Request) = Request(request.catId,
                request.name, request.description, request.votes + 1
        )
    }
}