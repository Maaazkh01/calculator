package com.maaz.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maaz.notes.databinding.ActivityViewNoteBinding

class ViewNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get correct data passed from intent
        val title = intent.getStringExtra("note_title") ?: "No Title"
        val description = intent.getStringExtra("note_description") ?: "No Description"

        binding.viewTitle.text = title
        binding.viewDescription.text = description
    }
}