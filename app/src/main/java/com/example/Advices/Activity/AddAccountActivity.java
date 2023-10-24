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

public class AddAccountActivity extends AppCompatActivity {
    EditText edtname, edtpass;
    Button add, trove;
    SQLite sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        edtname = findViewById(R.id.edtaccountname);
        edtpass = findViewById(R.id.edtaccountpass);
        add = findViewById(R.id.btnaddaccount);
        trove = findViewById(R.id.btnreturnaccount);

        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtname.getText()) || TextUtils.isEmpty(edtpass.getText()))
                    Toast.makeText(AddAccountActivity.this, "Chưa nhập đủ nội dung", Toast.LENGTH_SHORT).show();
                else
                {
                    sqLite.QueryData("INSERT INTO Account VALUES (null, '"+edtname.getText()+"', '"+edtpass.getText()+"')");
                    Toast.makeText(AddAccountActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edtname.setText("");
                    edtpass.setText("");
                }
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddAccountActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });
    }
}