package com.example.sm_projekt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sm_projekt.R;
import com.example.sm_projekt.models.Note;
import com.google.android.material.button.MaterialButton;

public class AddNoteActivity extends AppCompatActivity {
    Button cancelBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);
        cancelBtn = findViewById(R.id.backBtn2);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                Note note = new Note();
                note.setTitle(title);
                note.setContent(description);
                note.setTimestamp(createdTime);
                Toast.makeText(getApplicationContext(),R.string.noteSaved, Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",note);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",false);
                setResult(RESULT_CANCELED,returnIntent);
                finish();
            }
        });

    }
}
