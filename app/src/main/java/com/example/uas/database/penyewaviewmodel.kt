package com.example.uas.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class penyewaviewmodel(application: Application) : AndroidViewModel(application)
{
    private var repository: penyewarepository =
        penyewarepository(application)
    private var allNotes: LiveData<List<penyewa>> = repository.getAllNotes()
    fun insert(note: penyewa) {
        repository.insert(note)
    }
    fun update(note: penyewa) {
        repository.update(note)
    }
    fun delete(note: penyewa) {
        repository.delete(note)
    }
    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }
    fun getAllNotes(): LiveData<List<penyewa>> {
        return allNotes
    }
}
