package com.example.uas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.database.penyewa
import kotlinx.android.synthetic.main.item.view.*

class penyewaadapter : androidx.recyclerview.widget.ListAdapter<penyewa, penyewaadapter.NoteHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<penyewa>() {
            override fun areItemsTheSame(oldItem: penyewa, newItem: penyewa): Boolean {
                return oldItem.idpenyewa == newItem.idpenyewa }
            override fun areContentsTheSame(oldItem: penyewa, newItem: penyewa): Boolean {
                return oldItem.nama == newItem.nama && oldItem.noktp== newItem.noktp
                        && oldItem.alamat == newItem.alamat && oldItem.notelp == newItem.notelp
                        && oldItem.pickupdate == newItem.pickupdate && oldItem.returndate == newItem.returndate
                        && oldItem.jenismobil == newItem.jenismobil && oldItem.total == newItem.total } } }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NoteHolder(itemView) }
    override fun onBindViewHolder(holder: NoteHolder, position: Int) { val currentNote: penyewa = getItem(position)
        holder.textViewnama.text = currentNote.nama
        holder.textViewjenismobil.text = currentNote.jenismobil.toString()
        holder.textViewreturndate.text = currentNote.returndate }
    fun getNoteAt(position: Int): penyewa {
        return getItem(position) }
    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) { listener?.onItemClick(getItem(position)) } } }
        var textViewnama: TextView = itemView.text_view_nama
        var textViewjenismobil: TextView = itemView.text_view_mobil
        var textViewreturndate: TextView = itemView.text_view_return }
    interface OnItemClickListener {
        fun onItemClick(note: penyewa) }
    fun setOnItemClickListener(listener: OnItemClickListener) { this.listener = listener } }