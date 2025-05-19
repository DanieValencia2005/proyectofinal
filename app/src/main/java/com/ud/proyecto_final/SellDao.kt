package com.ud.proyecto_final


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SellDao {
    @Insert
    suspend fun insertarVenta(venta: SellEntity)

    @Query("SELECT * FROM ventas")
    suspend fun obtenerVentas(): List<SellEntity>
}
