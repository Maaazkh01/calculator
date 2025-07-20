package com.maaz.notes

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maaz.notes.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("MyNotes", MODE_PRIVATE)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val desc = binding.descriptionEditText.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty()) {
                val time = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                val note = "$title\n$desc\n$time"

                val notes = sharedPrefs.getStringSet("notes", mutableSetOf())!!.toMutableSet()
                notes.add(note)
                sharedPrefs.edit().putStringSet("notes", notes).apply()

                Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
