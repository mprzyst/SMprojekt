package com.example.sm_projekt.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sm_projekt.R;
import com.example.sm_projekt.activities.DeleteActivity;
import com.example.sm_projekt.activities.DetailsActivity;
import com.example.sm_projekt.activities.EditNoteActivity;
import com.example.sm_projekt.handlers.DBHandler;
import com.example.sm_projekt.models.Note;


import java.text.DateFormat;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Note>{

    private ArrayList<Note> mainList;
    Activity context;
    DBHandler dbHandler;
    TextView titleText;
    TextView subtitleText;
    TextView formatedTime;

    public ListAdapter(Activity applicationContext,
                       ArrayList<Note> Notes,
                       DBHandler dbHandler) {
        super(applicationContext, R.layout.note_list);
        this.context = applicationContext;
        this.mainList = Notes;
        this.dbHandler = dbHandler;
    }



    public void setMainList(ArrayList<Note> mainList) {
        this.mainList = mainList;
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
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.note_list, null,true);

        RelativeLayout listItem = (RelativeLayout) rowView.findViewById(R.id.listItem);
        titleText = (TextView) rowView.findViewById(R.id.textTitle);
        subtitleText = (TextView) rowView.findViewById(R.id.detail);
        formatedTime = (TextView) rowView.findViewById(R.id.datetime);

        titleText.setText(note.getTitle());
        subtitleText.setText(note.getContent());
        formatedTime.setText(DateFormat.getDateTimeInstance().format(note.getTimestamp()));


//
//
        try {
            listItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popup = new PopupMenu(context, v);
                    popup.getMenuInflater().inflate(R.menu.menu,
                            popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.edit:
                                    Intent intent = new Intent (context, EditNoteActivity.class);
                                    intent.putExtra("note",note);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivityForResult(intent, 2);


                                    break;
                                case R.id.delete:
                                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                    Intent intent1 = new Intent (context, DeleteActivity.class);
                                    intent1.putExtra("note",note);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivityForResult(intent1, 3);

                                    break;

                                default:
                                    break;
                            }

                            return true;
                        }
                    });
                return true;}
            });

            subtitleText.setOnTouchListener(new OnSwipeTouchListener(context) {
                @Override
                public void onSwipeRight() {
                    Toast.makeText(context, R.string.archived, Toast.LENGTH_SHORT).show();
                    dbHandler.editNotes(note, true);
                    mainList = dbHandler.readNotes(false);
                    notifyDataSetChanged();
                }
            });
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

        } catch (Exception e) {

            e.printStackTrace();
        }        return rowView;


//        return convertView;
    }

}
