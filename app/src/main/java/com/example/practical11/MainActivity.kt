package com.example.practical11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        androidx.appcompat.app.AppCompatActivity import
                androidx.core.view.WindowCompat import
                androidx.recyclerview.widget.DefaultItemAnimator import
                androidx.recyclerview.widget.LinearLayoutManager import
                androidx.recyclerview.widget.RecyclerView
        import com.example.madpractical_11.databinding.ActivityMainBinding import
        com.example.madpractical_11.databinding.NoteEditViewBinding

        class MainActivity : AppCompatActivity() {
            private lateinit var binding: ActivityMainBinding private val
        }
                TAG = "MainActivity"
        private var listener: ((note: Note, baseListAdapter: NotesAdapter, mode: NoteMode,
                                                             position: Int)->Unit)? =
                { note: Note, _: NotesAdapter, noteMode: NoteMode, pos: Int ->
                    note.modifiedTime = Note.getCurrentDateTime() if (noteMode
                    == NoteMode.add) { if (!createNote(note))
                    Toast.makeText(this, "Enter Valid Note", Toast.LENGTH_SHORT).show()
                } else if (noteMode == NoteMode.edit) {
                    Log.i(TAG, "listener: Note:$note") if
                                                               (!updateNote(note, pos))
                        Toast.makeText(this, "Enter Valid Note", Toast.LENGTH_SHORT).show()
                }
                }
            lateinit var db: DatabaseHelper private val notesList:
                    ArrayList<Note> = ArrayList<Note>() lateinit var notesRecycleAdapter:
                            NotesAdapter override
            fun onCreate(savedInstanceState: Bundle?) {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                super.onCreate(savedInstanceState)

                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

                db = DatabaseHelper(this)
                notesList.addAll(db.allNotes)

                notesRecycleAdapter = NotesAdapter(this, notesList) val mLayoutManager:
                        RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
                binding.listView1.layoutManager = mLayoutManager binding.listView1.itemAnimator =
                DefaultItemAnimator() binding.listView1.setHasFixedSize(true) binding.listView1.adapter =
                notesRecycleAdapter setSupportActionBar(binding.toolbar) binding.fab.setOnClickListener {
                    showAlertDialog(
                        NoteMode.add, "Add Note",
                        Note("", "", "", Note.getCurrentDateTime()), -1, notesRecycleAdapter)
                }
            }

