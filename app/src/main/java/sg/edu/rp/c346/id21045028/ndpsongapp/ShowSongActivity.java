package sg.edu.rp.c346.id21045028.ndpsongapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class ShowSongActivity extends AppCompatActivity {

    Button btnFilter;
    ListView SongList;
    ArrayList<Note> al;
    ArrayAdapter<Note> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        SongList = findViewById(R.id.SongList);
        btnFilter = findViewById(R.id.BtnFilter);

        al = new ArrayList<Note>();
        aa = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, al);
        SongList.setAdapter(aa);
        SongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);
                Intent i = new Intent(ShowSongActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        DBHelper dbh = new DBHelper(ShowSongActivity.this);
        al.clear();
        al.addAll(dbh.getAllNotes());
        aa.notifyDataSetChanged();

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowSongActivity.this);
                al.clear();
                String filterText = "*****";
                if(filterText.length() == 0) {
                    al.addAll(dbh.getAllNotes());
                }
                else{
                    al.addAll(dbh.getAllNotes(filterText));
                }
                aa.notifyDataSetChanged();
            }
        });
    }
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowSongActivity.this);
        al.clear();
        al.addAll(dbh.getAllNotes());
        aa.notifyDataSetChanged();
    }
}