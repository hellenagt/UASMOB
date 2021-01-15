package com.example.uas.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class penyewarepository (application: Application) {
    private var penyewaDAO: penyewaDAO
    private var allNotes: LiveData<List<penyewa>>

    init {
        val database: penyewadb = penyewadb.getInstance(
            application.applicationContext
        )!!
        penyewaDAO = database.penyewaDAO()
        allNotes = penyewaDAO.getAllNotes()
    }
    fun insert(note: penyewa) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(penyewaDAO).execute(note)
    }
    fun update(note: penyewa) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(penyewaDAO).execute(note)
    }
    fun delete(note: penyewa) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(penyewaDAO).execute(note)
    }
    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            penyewaDAO
        ).execute()
    }
    fun getAllNotes(): LiveData<List<penyewa>> {
        return allNotes
    }
    companion object {
        private class InsertNoteAsyncTask(penyewaDAO: penyewaDAO) : AsyncTask<penyewa, Unit, Unit>() {
            val penyewaDAO = penyewaDAO
            override fun doInBackground(vararg p0: penyewa?) {
                penyewaDAO.insert(p0[0]!!)
            }
        }
        private class UpdateNoteAsyncTask(penyewaDAO: penyewaDAO) : AsyncTask<penyewa, Unit, Unit>() {
            val penyewaDAO = penyewaDAO
            override fun doInBackground(vararg p0: penyewa?) {
                penyewaDAO.update(p0[0]!!)
            }
        }
        private class DeleteNoteAsyncTask(penyewaDAO: penyewaDAO) : AsyncTask<penyewa, Unit, Unit>() {
            val penyewaDAO = penyewaDAO
            override fun doInBackground(vararg p0: penyewa?) {
                penyewaDAO.delete(p0[0]!!)
            }
        }
        private class DeleteAllNotesAsyncTask(penyewaDAO: penyewaDAO) : AsyncTask<Unit, Unit, Unit>() {
            val penyewaDAO = penyewaDAO
            override fun doInBackground(vararg p0: Unit?) {
                penyewaDAO.deleteAllNotes()
            }
        }
    }
}