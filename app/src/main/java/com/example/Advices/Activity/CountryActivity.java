package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.Advices.Oject.Country;
import com.example.Advices.Adapter.CountryAdapter;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Country> arrayList;
    CountryAdapter countryAdapter;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        recyclerView = findViewById(R.id.recyclerviewcountry);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        countryAdapter = new CountryAdapter(arrayList, this);
        recyclerView.setAdapter(countryAdapter);

        //Tạo database
        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        //Tạo bảng
        sqLite.QueryData("CREATE TABLE IF NOT EXISTS Country(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", CountryName VARCHAR(200), CountryImage VARCHAR(500))");

        //Thêm dữ liệu
//        sqLite.QueryData("INSERT INTO Country VALUES (null, 'VietNam', 'https://cdn.discordapp.com/attachments/903473293644935168/1158372122087936051/2000px-Flag_of_Vietnam.png?ex=651c019b&is=651ab01b&hm=08ef466b5145d178729ab8508892444a7a55a4b21ade30300d495b925c27ce3f&')");
//        sqLite.QueryData("INSERT INTO Country VALUES (null, 'Japan', 'https://cdn.discordapp.com/attachments/903473293644935168/1158372262823591936/800px-Flag_of_Japan.png?ex=651c01bc&is=651ab03c&hm=61a5773c5a59a998d08266bc6299d6b98e93cee321ec84c4b15d6c26d42f7c0a&')");

        //Xóa dữ liệu
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '3'");
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '4'");
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '5'");
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '6'");
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '7'");
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '8'");
        ShowData();
    }

    private void ShowData() {
        Cursor sData = sqLite.GetData("SELECT * FROM Country");
        arrayList.clear();
        while (sData.moveToNext()){
            String image = sData.getString(2);
            String ten = sData.getString(1);
            int id = sData.getInt(0);
            arrayList.add(new Country(id, ten, image));
        }
        countryAdapter.notifyDataSetChanged();
    }
}