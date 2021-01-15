package com.example.uas.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface penyewaDAO {
    @Insert
    fun insert(note: penyewa)
    @Update
    fun update(note: penyewa)
    @Delete
    fun delete(note: penyewa)
    @Query("DELETE FROM penyewa_table")
    fun deleteAllNotes()
    @Query("SELECT * FROM penyewa_table ORDER BY pickupdate DESC")
    fun getAllNotes(): LiveData<List<penyewa>>

}