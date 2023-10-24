package com.example.Advices.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Advices.R;

public class AdminActivity extends AppCompatActivity {
    Button country, city, attraction, account, list, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        country = findViewById(R.id.btnCountry1);
        city = findViewById(R.id.btnCity1);
        attraction = findViewById(R.id.btnAttraction1);
        account = findViewById(R.id.btnAccount1);
        list = findViewById(R.id.btnShowList);
        logout = findViewById(R.id.btnLogout);

        ButtonFunction();
    }

    private void ButtonFunction() {
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AddCountryActivity.class);
                startActivity(i);
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AddCityActivity.class);
                startActivity(i);
            }
        });
        attraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AddAttractionActivity.class);
                startActivity(i);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AddAccountActivity.class);
                startActivity(i);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}