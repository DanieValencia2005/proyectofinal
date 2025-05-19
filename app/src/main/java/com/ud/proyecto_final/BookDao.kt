package com.ud.proyecto_final

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books WHERE type = :type")
    suspend fun getBooksByType(type: String): List<Book>
}