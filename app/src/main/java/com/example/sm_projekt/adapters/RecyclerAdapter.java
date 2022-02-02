//package com.example.sm_projekt.adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.PopupMenu;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.sm_projekt.R;
//import com.example.sm_projekt.activities.EditNoteActivity;
//import com.example.sm_projekt.models.Note;
//
//import java.text.DateFormat;
//
//import io.realm.Realm;
//import io.realm.RealmResults;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
//
//    Context context;
//    RealmResults<Note> notesList;
//
//    public RecyclerAdapter(Context context, RealmResults<Note> notesList) {
//        this.context = context;
//        this.notesList = notesList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.note_details,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
//        Note note = notesList.get(position);
//        holder.titleOutput.setText(note.getTitle());
//        holder.descriptionOutput.setText(note.getContent());
//
//        String formatedTime = DateFormat.getDateTimeInstance().format(note.getTimestamp());
//        holder.timeOutput.setText(formatedTime);
//
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                PopupMenu menu = new PopupMenu(context,v);
//                menu.getMenu().add("Delete");
//                menu.getMenu().add("Edit");
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if(item.getTitle().equals("Delete")){
//                            //delete the note
//                            Realm realm = Realm.getDefaultInstance();
//                            realm.beginTransaction();
//                            note.deleteFromRealm();
//                            realm.commitTransaction();
//                            Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show();
//                        }
//                        if(item.getTitle().equals("Edit")){
//                            Intent intent = new Intent (context, EditNoteActivity.class);
//                            intent.putExtra("node",note);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            context.startActivity(intent);
//
//                        }
//
//                        return true;
//                    }
//                });
//                menu.show();
//
//                return true;
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return notesList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView titleOutput;
//        TextView descriptionOutput;
//        TextView timeOutput;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleOutput = itemView.findViewById(R.id.titleoutput);
//            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
//            timeOutput = itemView.findViewById(R.id.timeoutput);
//        }
//    }
//
//    /*private ArrayList<Note> noteList;
//
//    public RecyclerAdapter(ArrayList<Note> noteList) {
//        this.noteList = noteList;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        private TextView titleText;
//        private TextView contentText;
//
//        public MyViewHolder(final View view){
//            super(view);
//            titleText = view.findViewById(R.id.title);
//            contentText = view.findViewById(R.id.content);
//
//        }
//    }
//
//    @NonNull
//    @Override
//    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_details, parent, false);
//        return new MyViewHolder(itemView);
//    }
//
//    //here we can change text of our textView
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
//        String title = noteList.get(position).getTitle();
//        String content = noteList.get(position).getContent();
//        holder.titleText.setText(title);
//        holder.contentText.setText(content);
//    }
//
//    @Override
//    public int getItemCount() {
//        return noteList.size();
//    }*/
//}
