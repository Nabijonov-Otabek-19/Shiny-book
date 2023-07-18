package uz.nabijonov.otabek.bookapp_bek.data.common

import java.io.Serializable

data class BookData(
    val author: String = "",
    val name: String = "",
    val bookCoverUrl: String = "",
    val page: String = "",
    val genre: String = "",
    val year: String = "",
    val bookUrl: String = "",
    val about : String = ""
) : Serializable
