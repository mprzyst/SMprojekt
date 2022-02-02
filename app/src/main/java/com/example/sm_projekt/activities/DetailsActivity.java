package com.example.sm_projekt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sm_projekt.R;
import com.example.sm_projekt.models.Note;
import com.google.android.material.button.MaterialButton;

public class DetailsActivity extends AppCompatActivity {

    Note note;
    TextView titleView;
    TextView descriptionView;
    MaterialButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details);

        note = (Note) getIntent().getSerializableExtra("note");

        titleView = findViewById(R.id.titleTxt);
        descriptionView = findViewById(R.id.contentTxt);
        backButton = findViewById(R.id.cancelButton);

        titleView.setText(note.getTitle());
        descriptionView.setText(note.getContent());

        backButton.setOnClickListener(new View.OnClickListener() {
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