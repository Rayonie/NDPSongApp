package sg.edu.rp.c346.id21045028.ndpsongapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnShow, btnRetrieve;
    TextView tvDBContent;
    EditText etContentSinger, etContentSongtitle, etContentYear;
    ArrayList<Note> al;
    RadioButton Rbtn1,Rbtn2,Rbtn3,Rbtn4,Rbtn5;
    ArrayAdapter<Note> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.BtnInsert);
        btnShow = findViewById(R.id.BtnShow);
        Rbtn1 = findViewById(R.id.radioButton);
        Rbtn2 = findViewById(R.id.radioButton2);
        Rbtn3 = findViewById(R.id.radioButton3);
        Rbtn4 = findViewById(R.id.radioButton4);
        Rbtn5 = findViewById(R.id.radioButton5);
        etContentYear = findViewById(R.id.etContentYear);
        etContentSongtitle = findViewById(R.id.etContentSongtitle);
        etContentSinger = findViewById(R.id.etContentSinger);
        //initialize the variables with UI here







        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Song = etContentSongtitle.getText().toString();
                String Singer = etContentSinger.getText().toString();
                String Year = etContentYear.getText().toString();
                String star = "";
                if(Rbtn1.isChecked()){
                     star = "*";
                }else if(Rbtn2.isChecked()){
                     star = "**";
                }else if(Rbtn3.isChecked()){
                     star = "***";
                }else if(Rbtn4.isChecked()){
                     star = "****";
                }else if(Rbtn5.isChecked()){
                     star = "*****";
                }
                String total = Song + "\n" + Singer + "\n " + Year + "\n" + star;
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(total);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ShowSongActivity.class);
                startActivity(i);
            }
        });


    }


}