package com.example.uas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.inputpenyewa.*

class addeditpenyewaactivity : AppCompatActivity() {
    companion object {
        const val EXTRA_IDPENYEWA = "com.piusanggoro.notesapp.EXTRA_IDPENYEWA"
        const val EXTRA_NAMA = "com.piusanggoro.notesapp.EXTRA_NAMA"
        const val EXTRA_NOKTP = "com.piusanggoro.notesapp.EXTRA_NOKTP"
        const val EXTRA_ALAMAT = "com.piusanggoro.notesapp.EXTRA_ALAMAT"
        const val EXTRA_NOTELP = "com.piusanggoro.notesapp.EXTRA_NOTELP"
        const val EXTRA_PICKUPDATE = "com.piusanggoro.notesapp.EXTRA_PICKUPDATE"
        const val EXTRA_RETURNDATE= "com.piusanggoro.notesapp.EXTRA_RETURNDATE"
        const val EXTRA_JENISMOBIL = "com.piusanggoro.notesapp.EXTRA_JENISMOBIL"
        const val EXTRA_TOTAL = "com.piusanggoro.notesapp.EXTRA_TOTAL"


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inputpenyewa)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        if (intent.hasExtra(EXTRA_IDPENYEWA)) {
            title = "Edit Penyewa"
            edit_text_nama.setText(intent.getStringExtra(EXTRA_NAMA))
            edit_text_ktp.setText(intent.getStringExtra(EXTRA_NOKTP))
            edit_text_alamat.setText (intent.getStringExtra(EXTRA_ALAMAT))
            edit_text_notelp.setText (intent.getStringExtra(EXTRA_NOTELP))
            edit_text_pickup.setText (intent.getStringExtra(EXTRA_PICKUPDATE))
            edit_text_return.setText (intent.getStringExtra(EXTRA_RETURNDATE))
            edit_text_jenis.setText (intent.getStringExtra(EXTRA_JENISMOBIL))
            edit_text_total.setText (intent.getStringExtra(EXTRA_TOTAL))
        } else {
            title = "Tambah Penyewa"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.inputmenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun saveNote() {
        if (edit_text_nama.text.toString().trim().isBlank() || edit_text_ktp.text.toString().trim().isBlank()
            || edit_text_alamat.text.toString().trim().isBlank() || edit_text_notelp.text.toString().trim().isBlank()
            || edit_text_pickup.text.toString().trim().isBlank() || edit_text_return.text.toString().trim().isBlank()
            || edit_text_jenis.text.toString().trim().isBlank() || edit_text_total.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Data kosong!", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra(EXTRA_NAMA, edit_text_nama.text.toString())
            putExtra(EXTRA_NOKTP, edit_text_ktp.text.toString())
            putExtra(EXTRA_ALAMAT, edit_text_alamat.text.toString())
            putExtra(EXTRA_NOTELP, edit_text_notelp.text.toString())
            putExtra(EXTRA_PICKUPDATE, edit_text_pickup.text.toString())
            putExtra(EXTRA_RETURNDATE, edit_text_return.text.toString())
            putExtra(EXTRA_JENISMOBIL, edit_text_jenis.text.toString())
            putExtra(EXTRA_TOTAL, edit_text_total.text.toString())
            if (intent.getIntExtra(EXTRA_IDPENYEWA, -1) != -1) {
                putExtra(EXTRA_IDPENYEWA, intent.getIntExtra(EXTRA_IDPENYEWA, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}   