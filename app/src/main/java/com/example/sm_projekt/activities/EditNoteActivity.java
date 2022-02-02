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

public class EditNoteActivity extends AppCompatActivity {

    Note toEdit;
    EditText titleInput;
    EditText descriptionInput;
    MaterialButton saveBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        toEdit = (Note) getIntent().getSerializableExtra("note");

         titleInput = findViewById(R.id.titleinput);
         descriptionInput = findViewById(R.id.descriptioninput);
         saveBtn = findViewById(R.id.savebtn);
         cancelBtn = findViewById(R.id.backBtn);


         titleInput.setText(toEdit.getTitle());
         descriptionInput.setText(toEdit.getContent());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();

                toEdit.setTitle(title);
                toEdit.setContent(description);
                Toast.makeText(getApplicationContext(),R.string.noteSaved, Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",toEdit);
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
