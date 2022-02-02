package com.example.sm_projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sm_projekt.activities.AddNoteActivity;
import com.example.sm_projekt.activities.ArchiveActivity;
import com.example.sm_projekt.adapters.ArchiveAdapter;
import com.example.sm_projekt.adapters.ListAdapter;
import com.example.sm_projekt.handlers.DBHandler;
import com.example.sm_projekt.models.Note;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private final int ADD_ACTIVITY = 1;

    private ArrayList<Note> notesList;
    private ListView listView;
    private Button addNoteButton;
    private Button viewArchiveButton;
    ListAdapter mAdapter;


    private DBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(MainActivity.this);

        listView = findViewById(R.id.listView);
        addNoteButton = findViewById(R.id.addNote);
        viewArchiveButton = findViewById(R.id.archive);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddNoteActivity.class),ADD_ACTIVITY);
            }
        });
        viewArchiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ArchiveActivity.class),ADD_ACTIVITY);
            }
        });
        notesList = dbHandler.readNotes(false);

        mAdapter = new ListAdapter(this, notesList, dbHandler);
        listView.setAdapter(mAdapter);
/*
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter myAdapter = new RecyclerAdapter(getApplicationContext(),notesList);
        recyclerView.setAdapter(myAdapter);*/

//        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
//            @Override
//            public void onChange(RealmResults<Note> notes) {
//                myAdapter.notifyDataSetChanged();
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                Note newNote = (Note) data.getSerializableExtra("result");
                dbHandler.addNewNote(newNote);
                notesList = dbHandler.readNotes(false);

                mAdapter.setMainList(notesList);
                mAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
            Note editedNote = (Note) data.getSerializableExtra("result");
            dbHandler.editNotes(editedNote, false);
            notesList = dbHandler.readNotes(false);

            mAdapter.setMainList(notesList);
            mAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
        if(requestCode == 3){
            if(resultCode == Activity.RESULT_OK){
                Note noteToDelete = (Note) data.getSerializableExtra("result");
                dbHandler.deleteNote(noteToDelete);
                notesList = dbHandler.readNotes(false);

                mAdapter.setMainList(notesList);
                mAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
        if(requestCode == 4){
            if(resultCode == Activity.RESULT_OK){
                System.out.println();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println();
            }
        }
    } //onActivityResult

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteButton = findViewById(R.id.addNote);
        recyclerView = findViewById(R.id.recyclerView);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
            }
        });

        notesList = new ArrayList<>();

        setNoteDetails();
        setAdapter();
//        setUpListViewListener();
    }*/

   /* private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(notesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setNoteDetails() {
        notesList.add(new Note("backasd", "ahsdbahss"));
        notesList.add(new Note("backasd", "ahsdbahss"));
        notesList.add(new Note("backasd", "ahsdbahss"));
        notesList.add(new Note("backasd", "ahsdbahss"));
        notesList.add(new Note("backasd", "ahsdbahss"));
    }
*/
    /*private void setUpListViewListener() {
        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                notesList.remove(i);
                notesAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }*/
// dodawanie itemku
    /*private void addItem(View view) {
        EditText input = findViewById(R.id.editText2);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            notesAdapter.add(itemText);
            input.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter text", Toast.LENGTH_LONG);
        }

    }*/
}