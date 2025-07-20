package com.maaz.notes

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maaz.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var adapter: NoteAdapter
    private var notesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("MyNotes", MODE_PRIVATE)

//        adapter = NoteAdapter(notesList, sharedPrefs)
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = adapter
//
//        binding.addNoteButton.setOnClickListener {
//            startActivity(Intent(this, AddNoteActivity::class.java))
//        }
        adapter = NoteAdapter(notesList,this) { title, description ->
            val intent = Intent(this, ViewNoteActivity::class.java)
            intent.putExtra("note_title", title)
            intent.putExtra("note_description", description)
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.addNoteButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        notesList.clear()
        val notes = sharedPrefs.getStringSet("notes", setOf()) ?: setOf()
        notesList.addAll(notes)
        adapter.notifyDataSetChanged()
    }
}