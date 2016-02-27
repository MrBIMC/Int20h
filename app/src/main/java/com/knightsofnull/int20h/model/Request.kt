package com.knightsofnull.int20h.model

/**
 * Created by yarolegovich on 27.02.2016.
 */
data class Request(val itemId: Int, val name: String, val description: String, val votes: Int) {
    companion object {
        fun incrementVotes(request: Request) = Request(request.itemId,
                request.name, request.description, request.votes + 1
        )
    }
}