package com.example.sm_projekt.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sm_projekt.MainActivity;
import com.example.sm_projekt.R;
import com.example.sm_projekt.adapters.ArchiveAdapter;
import com.example.sm_projekt.adapters.ListAdapter;
import com.example.sm_projekt.handlers.DBHandler;
import com.example.sm_projekt.models.Note;

import java.util.ArrayList;

public class ArchiveActivity extends Activity {

    private final int RETURN_ACTIVITY = 1;
    ArrayList<Note> QuestionForSliderMenu = new ArrayList<Note>();
    private Button viewNotesButton;
    private ArrayList<Note> notesList;
    private DBHandler dbHandler;
    ArchiveAdapter aAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.archive_view);
        viewNotesButton = findViewById(R.id.notes);
        dbHandler = new DBHandler(ArchiveActivity.this);
        listView = (ListView) findViewById(R.id.listView);

        viewNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ArchiveActivity.this, MainActivity.class),RETURN_ACTIVITY);
            }
        });

        notesList = dbHandler.readNotes(true);

        aAdapter = new ArchiveAdapter(this, notesList, dbHandler);

        listView.setAdapter(aAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RETURN_ACTIVITY) {
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }
}