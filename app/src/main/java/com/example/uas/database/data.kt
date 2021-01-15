package com.example.uas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penyewa_table")
data class penyewa(
    var nama: String,
    var noktp: String,
    var alamat: String,
    var notelp: String,
    var pickupdate: String,
    var returndate: String,
    var jenismobil: String,
    var total: String

) {
    @PrimaryKey(autoGenerate = true)
    var idpenyewa: Int = 0
}