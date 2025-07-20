package com.maaz.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maaz.notes.databinding.ItemNoteBinding

class NoteAdapter(
    private val notes: List<String>,
    private val context: android.content.Context,
    private val onNoteClick: (String, String) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        val parts = note.split("||")
        val title = parts.getOrNull(0) ?: "No Title"
        val timestamp = parts.getOrNull(1) ?: ""
        val description = parts.getOrNull(2) ?: "No Description"

        holder.binding.titleTextView.text = title
        holder.binding.timestampTextView.text = timestamp

        holder.binding.root.setOnClickListener {
            onNoteClick(title, description)
        }
    }

    override fun getItemCount(): Int = notes.size
}

//package com.maaz.notes
//
//import android.app.AlertDialog
//import android.content.SharedPreferences
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class NoteAdapter(
//    private val notesList: MutableList<String>,
//    private val sharedPrefs: SharedPreferences
//) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
//
//    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val titleText: TextView = itemView.findViewById(R.id.titleText)
//        val timestampText: TextView = itemView.findViewById(R.id.timestampText)
//        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
//        return NoteViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//        val fullText = notesList[position]
//        val parts = fullText.split("::")
//        val title = parts[0]
//        val timestamp = if (parts.size > 1) parts[1] else ""
//
//        holder.titleText.text = title
//        holder.timestampText.text = timestamp
//        holder.deleteButton.setOnClickListener {
//            val context = holder.itemView.context
//
//            // Show confirmation dialog
//            AlertDialog.Builder(context).apply {
//                setTitle("Delete Note")
//                setMessage("Are you sure you want to delete this note?")
//                setPositiveButton("Yes") { _, _ ->
//                    notesList.removeAt(position)
//                    notifyItemRemoved(position)
//                    saveNotesToPrefs()
//                }
//                setNegativeButton("No", null)
//                show()
//            }
//        }
//
//
//    }
//
//    override fun getItemCount() = notesList.size
//
//    private fun saveNotesToPrefs() {
//        with(sharedPrefs.edit()) {
//            putStringSet("notes", notesList.toSet())
//            apply()
//        }
//    }
//}