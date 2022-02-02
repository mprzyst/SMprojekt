package com.example.sm_projekt.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sm_projekt.R;
import com.example.sm_projekt.activities.DetailsActivity;
import com.example.sm_projekt.activities.EditNoteActivity;
import com.example.sm_projekt.handlers.DBHandler;
import com.example.sm_projekt.models.Note;

import java.text.DateFormat;
import java.util.ArrayList;

public class ArchiveAdapter extends ArrayAdapter<Note> {

    private ArrayList<Note> mainList;
    Activity context;
    DBHandler dbHandler;
    String formatedTime;
    TextView titleText;
    TextView subtitleText;

    public ArchiveAdapter(Activity applicationContext,
                          ArrayList<Note> Notes,
                          DBHandler dbHandler) {
        super(applicationContext, R.layout.note_list);
        this.context = applicationContext;
        this.mainList = Notes;
        this.dbHandler = dbHandler;

    }

    @Override
    public int getCount() {
        return this.mainList.size();
    }

    @Override
    public Note getItem(int position) {
        return this.mainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ClickableViewAccessibility")
    public View getView(int position, View convertView, ViewGroup parent) {

        final Note note = getItem(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.note_list, null, true);


        RelativeLayout listItem = (RelativeLayout) rowView.findViewById(R.id.listItem);
        titleText = (TextView) rowView.findViewById(R.id.textTitle);
        subtitleText = (TextView) rowView.findViewById(R.id.detail);

        titleText.setText(note.getTitle());
        subtitleText.setText(note.getContent());
        formatedTime = DateFormat.getDateTimeInstance().format(note.getTimestamp());


        try {
            listItem.setOnClickListener(new View.OnClickListener() {
                //
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent (context, DetailsActivity.class);
                    intent1.putExtra("note",note);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivityForResult(intent1, 4);
                }
            });
            subtitleText.setOnTouchListener(new OnSwipeTouchListener(context) {
                @Override
                public void onSwipeRight() {
                    Toast.makeText(context, R.string.movedToNotes, Toast.LENGTH_SHORT).show();
                    dbHandler.editNotes(note, false);
                    mainList = dbHandler.readNotes(true);
                    notifyDataSetChanged();
                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }        return rowView;
    }
}
