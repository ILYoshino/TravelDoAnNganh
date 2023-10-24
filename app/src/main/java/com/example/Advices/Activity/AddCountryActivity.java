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

import com.example.Advices.Adapter.AddCountryAdapter;
import com.example.Advices.Adapter.CountryAdapter;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddCountryActivity extends AppCompatActivity {
    EditText name, img;
    Button add, trove;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        name = findViewById(R.id.edtcountryname);
        img = findViewById(R.id.edtcountryimg);
        add = findViewById(R.id.btnaddcountry);
        trove = findViewById(R.id.btnreturncountry);

        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(img.getText()))
                    Toast.makeText(AddCountryActivity.this, "Chưa nhập đủ nội dung", Toast.LENGTH_SHORT).show();
                else{
                sqLite.QueryData("INSERT INTO Country VALUES (null, '"+name.getText()+"', '"+img.getText()+"')");
                Toast.makeText(AddCountryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                name.setText("");
                img.setText("");
                }
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddCountryActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });
    }
}