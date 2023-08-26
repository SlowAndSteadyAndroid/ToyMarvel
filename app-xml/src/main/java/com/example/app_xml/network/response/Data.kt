package com.example.app_xml.network.response

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
) {
    fun isLast(): Boolean = offset >= total

}