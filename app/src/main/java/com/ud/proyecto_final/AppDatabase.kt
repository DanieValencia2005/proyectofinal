package com.ud.proyecto_final

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Agrega todas las entidades que usas en tu base de datos
@Database(entities = [User::class, Book::class, SellEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun sellDao(): SellDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bookstore_db" // Nombre único para la DB
                )
                    // Puedes agregar migraciones o fallbackToDestructiveMigration() si quieres
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}