package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.Advices.Adapter.AttractionAdapter;
import com.example.Advices.Adapter.CityAdapter;
import com.example.Advices.Oject.Attraction;
import com.example.Advices.Oject.City;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import java.util.ArrayList;
import java.util.List;

public class AttractionActivity extends AppCompatActivity {
    List<Attraction> cityList;
    RecyclerView recyclerView;
    ArrayList<Attraction> arrayList;
    AttractionAdapter attractionAdapter;
    TextView maincard;
    SQLite sqLite;
    int cityid;
    String cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        Intent i = getIntent();
        cityid = i.getIntExtra("cityid", 1);
        cityname = i.getStringExtra("cityname");

        maincard = findViewById(R.id.attractioncenter);
        maincard.setText(cityname);

        recyclerView = findViewById(R.id.recyclerviewattraction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        attractionAdapter = new AttractionAdapter(arrayList, this);
        recyclerView.setAdapter(attractionAdapter);

        //Tạo database
        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        //Tạo bảng
        sqLite.QueryData("CREATE TABLE IF NOT EXISTS Attraction(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", LocationName VARCHAR(200), LocationAddress VARCHAR(200), LocationPicture VARCHAR(200), Description VARCHAR(1000), Latitude VARCHAR(200), Longitude VARCHAR(200), Idf INTEGER)");

        //Thêm dữ liệu

//        sqLite.QueryData("INSERT INTO Attraction VALUES (null, 'Bưu điện Thành phố Hồ Chí Minh', '125 Hai Bà Trưng, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh, Việt Nam'" +
//                ", 'https://cdn.discordapp.com/attachments/903473293644935168/1163550694473736273/AF1QipObuhAiHUMSXvwHPpaUb8GrVSZ_605KyDGE2wLdw408-h272-k-no.png?ex=653ffc07&is=652d8707&hm=81206c45e26a9db31ea4f3e4a2c51b1011baf1b5de19815c745dba5fc3fbb55b&'" +
//                ", 'Bưu điện Thành phố Hồ Chí Minh là một điểm đến mang tính biểu tượng của một thành phố được mệnh danh là Hòn ngọc viễn Đông. Với kiểu kiến trúc độc đáo, khi bước chân vào Bưu điện Thành phố Hồ Chí Minh mình cảm nhận không gian thật rộng, thoáng, mát và rất sang trọng.'" +
//                ", '10.779884', '106.699943', 1)");
        ShowData();
    }

    private void ShowData() {
        Cursor sData = sqLite.GetData("SELECT * FROM Attraction WHERE Idf = "+cityid);
        arrayList.clear();
        while (sData.moveToNext()){
            int id = sData.getInt(0);
            String ten = sData.getString(1);
            String address = sData.getString(2);
            String pic = sData.getString(3);
            String desc = sData.getString(4);
            String lat = sData.getString(5);
            String Long = sData.getString(6);
            int idf = sData.getInt(7);
            arrayList.add(new Attraction(id, ten, address, pic, desc, lat, Long, idf));
        }
        attractionAdapter.notifyDataSetChanged();
    }
}