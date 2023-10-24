package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Advices.Adapter.AddCountryAdapter;
import com.example.Advices.Adapter.CommentAdapter;
import com.example.Advices.Oject.Comment;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TextView name, address, desc;
    EditText edtname, edtdesc;
    ImageView img, map;
    Button addComment;
    ArrayList<Comment> comments;
    String aname, aaddress, adesc, apic, alat, aLong;
    int aid, aidf;
    ListView lv;
    CommentAdapter commentAdapter;
    SQLite sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intentCatcher();
        reflect();

        comments = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, R.layout.lv_review, comments);
        lv.setAdapter(commentAdapter);

        //Tạo database
        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        //Tạo bảng
        sqLite.QueryData("CREATE TABLE IF NOT EXISTS Comment(CountryName VARCHAR(200), CountryImage VARCHAR(500), Idf INTEGER)");

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLite.QueryData("INSERT INTO Comment VALUES ('"+edtname.getText()+"', '"+edtdesc.getText()+"', "+aid+")");
                ShowData();
            }
        });
        ShowData();

        map.setClickable(true);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                intent.putExtra("positionname", aname);
                intent.putExtra("positionlat", alat);
                intent.putExtra("positionlong", aLong);
                startActivity(intent);
            }
        });

        Glide.with(this)
                .load(apic)
                .into(img);
        name.setText(aname);
        address.setText(aaddress);
        desc.setText(adesc);
    }

    private void reflect() {
        img = findViewById(R.id.imgDetail);
        name = findViewById(R.id.tvDetailName);
        address = findViewById(R.id.tvDetailAddress);
        desc = findViewById(R.id.tvDetailDescription);
        map = findViewById(R.id.map);
        lv = findViewById(R.id.lvcomment);
        edtname = findViewById(R.id.edtcommentname);
        edtdesc = findViewById(R.id.edtcommentdesc);
        addComment = findViewById(R.id.btncomment);
    }

    private void intentCatcher() {
        Intent i = getIntent();
        aid = i.getIntExtra("attractionid", 1);
        aname = i.getStringExtra("attractionname");
        aaddress = i.getStringExtra("attractionaddress");
        adesc = i.getStringExtra("attractiondesc");
        apic = i.getStringExtra("attractionimg");
        alat = i.getStringExtra("attractionlat");
        aLong = i.getStringExtra("attractionlong");
        aidf = i.getIntExtra("attractionidf", 1);
    }

    private void ShowData() {
        Cursor sData = sqLite.GetData("SELECT * FROM Comment WHERE Idf = "+ aid +"");
        comments.clear();
        while (sData.moveToNext()){
            String desc = sData.getString(1);
            String ten = sData.getString(0);
            int id = sData.getInt(2);
            comments.add(new Comment(ten, desc, id));
        }
        commentAdapter.notifyDataSetChanged();
    }
}