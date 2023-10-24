package com.example.Advices.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Advices.Adapter.AddAttractionAdapter;
import com.example.Advices.Adapter.AddCityAdapter;
import com.example.Advices.Oject.Attraction;
import com.example.Advices.Oject.City;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import java.util.ArrayList;

public class AddAttractionActivity extends AppCompatActivity {
    EditText name, img, desc, idf, lat, aLong, address;
    Button add, trove;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attraction);

        name = findViewById(R.id.edtattractionname);
        desc = findViewById(R.id.edtattractiondesc);
        img = findViewById(R.id.edtattractionimg);
        idf = findViewById(R.id.edtattractionidf);
        lat = findViewById(R.id.edtattractionlat);
        aLong = findViewById(R.id.edtattractionlong);
        address = findViewById(R.id.edtattractionaddress);

        add = findViewById(R.id.btnaddattraction);
        trove = findViewById(R.id.btnreturnattraction);

        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(desc.getText()) || TextUtils.isEmpty(img.getText()) || TextUtils.isEmpty(lat.getText()) || TextUtils.isEmpty(aLong.getText()) || TextUtils.isEmpty(idf.getText()) || TextUtils.isEmpty(address.getText()))
                    Toast.makeText(AddAttractionActivity.this, "Chưa nhập đủ nội dung", Toast.LENGTH_SHORT).show();
                else{
                    sqLite.QueryData("INSERT INTO Attraction VALUES (null, '"+name.getText()+"', '"+address.getText()+"', '"+img.getText()+"', '"+desc.getText()+"', '"+lat.getText()+"', '"+aLong.getText()+"', '"+idf.getText()+"')");
                    Toast.makeText(AddAttractionActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    desc.setText("");
                    img.setText("");
                    idf.setText("");
                    lat.setText("");
                    aLong.setText("");
                    address.setText("");
                }
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddAttractionActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });
    }
}