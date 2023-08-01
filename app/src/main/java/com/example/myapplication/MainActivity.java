package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button buttonAddNote;
    private ListView listViewNotes;
    private List<Note> notes;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        listViewNotes = findViewById(R.id.listViewNotes);

        notes = new ArrayList<>();
        adapter = new NoteAdapter(this, notes);
        listViewNotes.setAdapter(adapter);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editTextNote.getText().toString();
                if (!noteText.isEmpty()) {
                    Note note = new Note(noteText);
                    notes.add(note);
                    adapter.notifyDataSetChanged();
                    editTextNote.setText("");
                }
            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noteText = editTextNote.getText().toString();
                if (!noteText.isEmpty()) {
                    Note note = notes.get(position);
                    note.setText(noteText);
                    adapter.notifyDataSetChanged();
                    editTextNote.setText("");
                }
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                notes.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}