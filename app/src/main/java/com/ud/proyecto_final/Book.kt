package com.ud.proyecto_final

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val publisher: String,
    val year: Int,
    val price: Double,
    val condition: String,
    val notes: String,
    val type: String // "venta" o "compra"
)