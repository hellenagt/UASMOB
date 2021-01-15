package com.example.uas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.database.penyewa
import com.example.uas.database.penyewaviewmodel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profile.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }
    private lateinit var  penyewaViewModel: penyewaviewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, addeditpenyewaactivity::class.java),
                ADD_NOTE_REQUEST
            ) }
        buttonProfil.setOnClickListener{
            val intentMain = Intent(this, profile::class.java)
            startActivity(intentMain)
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter = penyewaadapter()
        recycler_view.adapter = adapter
        penyewaViewModel = ViewModelProviders.of(this).get(penyewaViewModel::class.java)
        penyewaViewModel.getAllNotes().observe(this, Observer<List<penyewa>> {
            adapter.submitList(it)
        })
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                penyewaViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "data penyewa dihapus!", Toast.LENGTH_SHORT).show()
            } }
        ).attachToRecyclerView(recycler_view)
        adapter.setOnItemClickListener(object : penyewaadapter.OnItemClickListener {
            override fun onItemClick(note: penyewa) {
                val intent = Intent(baseContext, addeditpenyewaactivity::class.java)
                intent.putExtra(addeditpenyewaactivity.EXTRA_IDPENYEWA, note.idpenyewa)
                intent.putExtra(addeditpenyewaactivity.EXTRA_NAMA, note.nama)
                intent.putExtra(addeditpenyewaactivity.EXTRA_NOKTP, note.noktp)
                intent.putExtra(addeditpenyewaactivity.EXTRA_ALAMAT, note.alamat)
                intent.putExtra(addeditpenyewaactivity.EXTRA_NOTELP, note.notelp)
                intent.putExtra(addeditpenyewaactivity.EXTRA_PICKUPDATE, note.pickupdate)
                intent.putExtra(addeditpenyewaactivity.EXTRA_RETURNDATE, note.returndate)
                intent.putExtra(addeditpenyewaactivity.EXTRA_JENISMOBIL, note.jenismobil)
                intent.putExtra(addeditpenyewaactivity.EXTRA_TOTAL, note.total)

                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            } }) }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_notes -> {
                penyewaViewModel.deleteAllNotes()
                Toast.makeText(this, "Semua data sudah dihapus!", Toast.LENGTH_SHORT).show()
                true }
            else -> {
                super.onOptionsItemSelected(item) } } }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newNote = penyewa(
                data!!.getStringExtra(addeditpenyewaactivity.EXTRA_NAMA).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_NOKTP).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_ALAMAT).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_NOTELP).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_PICKUPDATE).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_RETURNDATE).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_JENISMOBIL).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_TOTAL).toString()
                )
                penyewaViewModel.insert(newNote)
            Toast.makeText(this, "data penyewa disimpan!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(addeditpenyewaactivity.EXTRA_IDPENYEWA, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan gagal!", Toast.LENGTH_SHORT).show()
            }
            val updatepenyewa = penyewa(
                data!!.getStringExtra(addeditpenyewaactivity.EXTRA_NAMA).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_NOKTP).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_ALAMAT).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_NOTELP).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_PICKUPDATE).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_RETURNDATE).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_JENISMOBIL).toString(),
                data.getStringExtra(addeditpenyewaactivity.EXTRA_TOTAL).toString()
            )
            updatepenyewa.idpenyewa = data.getIntExtra(addeditpenyewaactivity.EXTRA_IDPENYEWA, -1)
            penyewaViewModel.update(updatepenyewa)
        } else {
            Toast.makeText(this, "data penyewa tidak disimpan!", Toast.LENGTH_SHORT).show() } } }
