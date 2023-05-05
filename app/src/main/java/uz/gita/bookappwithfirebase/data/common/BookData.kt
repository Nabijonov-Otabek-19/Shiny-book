package uz.gita.bookappwithfirebase.data.common

data class BookData(
    val author: String,
    val name: String,
    val bookCoverUrl: Int,
    val genre: String,
    val year: String,
    val bookUrl: String
)
