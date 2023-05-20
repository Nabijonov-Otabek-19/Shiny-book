package uz.gita.bookappwithfirebase.utils

object Constants {

    // CN means Collection
    const val CN_BOOK_NAME = "books"
    const val CN_CATEGORY_NAME = "categories"

    // SG means Storage
    const val SG_FILE_PATH = "books/"

    val categoryList = ArrayList<String>()

    init {
        categoryList.add("Programming")
        categoryList.add("Psychology")
        categoryList.add("Motivation")
        categoryList.add("English")
    }
}
