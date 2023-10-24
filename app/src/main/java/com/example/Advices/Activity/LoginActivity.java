package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Advices.Oject.Country;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

public class LoginActivity extends AppCompatActivity {
    SQLite sqLite;
    EditText name, pass;
    Button access;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.edtLoginEmail);
        pass = findViewById(R.id.edtPassword);
        access = findViewById(R.id.btnLoginMain);

        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor sData = sqLite.GetData("SELECT * FROM Account");
                while (sData.moveToNext()) {
                    String lname = sData.getString(1);
                    String lpass = sData.getString(2);
                    if (lname.equals(name.getText().toString()) && lpass.equals(pass.getText().toString()))
                    {
                        Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Tạo database
        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        //Tạo bảng
        sqLite.QueryData("CREATE TABLE IF NOT EXISTS Account(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", AccountName VARCHAR(200), AccountPass VARCHAR(500))");

        //Thêm dữ liệu
//        sqLite.QueryData("INSERT INTO Account VALUES (null, 'test123', 'test123')");
        //Xóa dữ liệu
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '3'");
//        sqLite.QueryData("DELETE FROM Country WHERE Id = '4'");

    }
}