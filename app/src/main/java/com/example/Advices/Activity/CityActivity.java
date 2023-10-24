package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Advices.Oject.City;
import com.example.Advices.Adapter.CityAdapter;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    List<City> cityList;
    RecyclerView recyclerView;
    ArrayList<City> arrayList;
    CityAdapter cityAdapter;
    SQLite sqLite;
    private int countryId;
    private String countryName;
    TextView cname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        cname = findViewById(R.id.countryname);

        //Lấy CountryId
        Intent intent = getIntent();
        countryId = intent.getIntExtra("countryid", 1);
        countryName = intent.getStringExtra("countryname");

        cname.setText(countryName);

        recyclerView = findViewById(R.id.recyclerviewcity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        cityAdapter = new CityAdapter(arrayList, this);
        recyclerView.setAdapter(cityAdapter);

        //Tạo database
        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        //Tạo bảng
        sqLite.QueryData("CREATE TABLE IF NOT EXISTS Cities(Id INTEGER PRIMARY KEY AUTOINCREMENT, CityName VARCHAR(200), CityImage VARCHAR(500), State VARCHAR(200), CountryId INTEGER)");

        //Thêm dữ liệu
//                sqLite.QueryData("INSERT INTO Cities VALUES (null, 'TpHCM', 'https://cdn.discordapp.com/attachments/903473293644935168/1160567653426020463/hcm-165158859327117805465204052022064307-16644446260941483915055.png?ex=653521da&is=6522acda&hm=42966384e3a033d67e1363ba9966c12cf76388d566d3f4d598b0fb24ff655c2f&'" +
//                        ", '', 1)");

        //Xóa dữ liệu
//        sqLite.QueryData("DELETE FROM City WHERE Id = '1'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '2'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '3'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '4'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '5'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '6'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '7'");
//        sqLite.QueryData("DELETE FROM City WHERE Id = '8'");

        ShowData();
    }

    private void ShowData() {
        Cursor sData = sqLite.GetData("SELECT * FROM Cities WHERE CountryId = "+countryId);
        arrayList.clear();
        while (sData.moveToNext()){
            int id = sData.getInt(0);
            String ten = sData.getString(1);
            String image = sData.getString(2);
            String tinh = sData.getString(3);
            int idf = sData.getInt(4);
            arrayList.add(new City(id, ten, image, tinh, idf));
        }
        cityAdapter.notifyDataSetChanged();
    }
}