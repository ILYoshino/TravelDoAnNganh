package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

public class AddCityActivity extends AppCompatActivity {
    EditText name, state, img, idf;
    Button add, trove;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        name = findViewById(R.id.edtcityname);
        state = findViewById(R.id.edtcitystate);
        img = findViewById(R.id.edtcityimg);
        idf = findViewById(R.id.edtcityidf);

        add = findViewById(R.id.btnaddcity);
        trove = findViewById(R.id.btnreturncity);

        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(idf.getText()))
                    Toast.makeText(AddCityActivity.this, "Chưa nhập đủ nội dung!", Toast.LENGTH_SHORT).show();
                else {
                    sqLite.QueryData("INSERT INTO Cities VALUES (null, '" + name.getText() + "', '" + img.getText() + "', '" + state.getText() + "', " + idf.getText() + ")");
                    Toast.makeText(AddCityActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    img.setText("");
                    state.setText("");
                    idf.setText("");
                }
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddCityActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });
    }

}