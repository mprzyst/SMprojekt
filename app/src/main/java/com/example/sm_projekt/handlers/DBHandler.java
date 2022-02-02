package com.example.sm_projekt.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sm_projekt.models.Note;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "smdb";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "note";
    private static final String ID_COL = "id";
    private static final String TITLE_COL = "title";
    private static final String CONTENT_COL = "content";
    private static final String TIMESTAMP_COL = "timestamp";
    private static final String ARCHIVED_COL = "isArchived";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + CONTENT_COL + " TEXT,"
                + TIMESTAMP_COL + " TEXT,"
                + ARCHIVED_COL + " INTEGER)";

        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewNote(Note newNote) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, newNote.getTitle());
        values.put(CONTENT_COL, newNote.getContent());
        values.put(TIMESTAMP_COL, newNote.getTimestamp());
        values.put(ARCHIVED_COL, false);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Note> readNotes(boolean isArchived) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorNotes = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ARCHIVED_COL + " = " + (isArchived ? 1 : 0), null);
        ArrayList<Note> notesModalList = new ArrayList<>();

        if (cursorNotes.moveToFirst()) {
            do {
                notesModalList.add(new Note(
                        cursorNotes.getInt(0),
                        cursorNotes.getString(1),
                        cursorNotes.getString(2),
                        Long.parseLong(cursorNotes.getString(3))));
            } while (cursorNotes.moveToNext());
        }

        cursorNotes.close();
        return notesModalList;
    }


    // below is the method for deleting our course.
    public void editNotes(Note noteToEdit, boolean isArchived) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE_COL, noteToEdit.getTitle());
        values.put(CONTENT_COL, noteToEdit.getContent());
        values.put(TIMESTAMP_COL, noteToEdit.getTimestamp());
        values.put(ARCHIVED_COL, isArchived);

        db.update(TABLE_NAME, values, "id=?", new String[]{noteToEdit.getId().toString()});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteNote(Note noteToDelete) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COL + "=?", new String[]{noteToDelete.getId().toString()});

        db.close();
    }



}
