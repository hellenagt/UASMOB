package com.example.uas.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [penyewa::class], version = 1)
abstract class penyewadb : RoomDatabase() {
    abstract fun penyewaDAO(): penyewaDAO
    companion object {
        private var instance: penyewadb? = null
        fun getInstance(context: Context): penyewadb? {
            if (instance == null) {
                synchronized(penyewadb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        penyewadb::class.java, "penyewa_database" )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build() } }
            return instance }
        fun destroyInstance() {
            instance = null }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute() } } }
    class PopulateDbAsyncTask(db: penyewadb?) : AsyncTask<Unit, Unit, Unit>() {
        private val penyewaDAO = db?.penyewaDAO()
        override fun doInBackground(vararg p0: Unit?) {
            penyewaDAO?.insert(penyewa("Hellen", "3426719100009", "Ds. Pagerwojo", "08243175391", "3 januari 2021","5 januari 2021", "Toyota Avanza B678L","345000")) } } }
