package com.example.Advices.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Advices.Adapter.AddAccountAdapter;
import com.example.Advices.Adapter.AddAttractionAdapter;
import com.example.Advices.Adapter.AddCityAdapter;
import com.example.Advices.Adapter.AddCountryAdapter;
import com.example.Advices.Oject.Account;
import com.example.Advices.Oject.Attraction;
import com.example.Advices.Oject.City;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;
import com.example.Advices.SQLPrompt.SQLite;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    Button btncou, btncity, btnatt, btnacc;
    SQLite sqLite;
    ArrayList<Country> countries;
    ArrayList<Account> accounts;
    ArrayList<City> cities;
    ArrayList<Attraction> attractions;
    ListView lv;
    AddAttractionAdapter attractionAdapter;
    AddCountryAdapter countryAdapter;
    AddCityAdapter cityAdapter;
    AddAccountAdapter accountAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Reflect();

        countries = new ArrayList<>();
        countryAdapter = new AddCountryAdapter(this, R.layout.lv_addlist, countries);
        cities = new ArrayList<>();
        cityAdapter = new AddCityAdapter(this, R.layout.lv_addlist, cities);
        attractions = new ArrayList<>();
        attractionAdapter = new AddAttractionAdapter(this, R.layout.lv_addlist, attractions);
        accounts = new ArrayList<>();
        accountAdapter = new AddAccountAdapter(this, R.layout.lv_account, accounts);

        sqLite = new SQLite(getApplicationContext(), "Travel.sqlite", null, 1);

        btncou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setAdapter(countryAdapter);
                ShowDataCountry();
            }
        });
        btncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setAdapter(cityAdapter);
                ShowDataCity();
            }
        });
        btnatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setAdapter(attractionAdapter);
                ShowDataAttraction();
            }
        });
        btnacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setAdapter(accountAdapter);
                ShowDataAccount();
            }
        });
    }

    private void Reflect() {
        btncou = findViewById(R.id.btnlistcountry);
        btncity = findViewById(R.id.btnlistcity);
        btnatt = findViewById(R.id.btnlistattraction);
        btnacc = findViewById(R.id.btnlistaccount);
        lv = findViewById(R.id.listviewall);
    }
    private void ShowDataCity() {
        Cursor sData = sqLite.GetData("SELECT * FROM Cities");
        cities.clear();
        while (sData.moveToNext()){
            int id = sData.getInt(0);
            String ten = sData.getString(1);
            String image = sData.getString(2);
            String tinh = sData.getString(3);
            int idf = sData.getInt(4);
            cities.add(new City(id, ten, image, tinh, idf));
        }
        cityAdapter.notifyDataSetChanged();
    }
    private void ShowDataAttraction() {
        Cursor sData = sqLite.GetData("SELECT * FROM Attraction");
        attractions.clear();
        while (sData.moveToNext()){
            int id = sData.getInt(0);
            String ten = sData.getString(1);
            String address = sData.getString(2);
            String pic = sData.getString(3);
            String desc = sData.getString(4);
            String lat = sData.getString(5);
            String Long = sData.getString(6);
            int idf = sData.getInt(7);
            attractions.add(new Attraction(id, ten, address, pic, desc, lat, Long, idf));
        }
        attractionAdapter.notifyDataSetChanged();
    }
    private void ShowDataCountry() {
        Cursor sData = sqLite.GetData("SELECT * FROM Country");
        countries.clear();
        while (sData.moveToNext()){
            String image = sData.getString(2);
            String ten = sData.getString(1);
            int id = sData.getInt(0);
            countries.add(new Country(id, ten, image));
        }
        countryAdapter.notifyDataSetChanged();
    }
    private void ShowDataAccount() {
        Cursor sData = sqLite.GetData("SELECT * FROM Account");
        accounts.clear();
        while (sData.moveToNext()){
            String pass = sData.getString(2);
            String ten = sData.getString(1);
            int id = sData.getInt(0);
            accounts.add(new Account(id, ten, pass));
        }
        accountAdapter.notifyDataSetChanged();
    }

    public void DialogUpdateCountry(String ten, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_country_edit);

        EditText edtEdit = dialog.findViewById(R.id.edtEdit);
        Button btnEdit = dialog.findViewById(R.id.btnEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        edtEdit.setText(ten);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newND = edtEdit.getText().toString().trim();
                if(TextUtils.isEmpty(newND)){
                    Toast.makeText(ListActivity.this, "Chưa nhập đủ nội dung!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                sqLite.QueryData("UPDATE Country SET CountryName = '"+ newND +"' WHERE Id = '"+ id +"'");
                dialog.dismiss();
                ShowDataCountry();
            }
        });
        dialog.show();
    }

    public void DialogDeleteCountry(String ten,int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn xóa " + ten + "?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sqLite.QueryData("DELETE FROM Country WHERE Id = '"+ id +"'");
                ShowDataCountry();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void DialogUpdateCity(String ten, String state, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_city_edit);

        EditText edtEdit = dialog.findViewById(R.id.edtCityName);
        EditText edtEditS = dialog.findViewById(R.id.edtCityState);
        Button btnEdit = dialog.findViewById(R.id.btnCityEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCityCancel);

        edtEditS.setText(state);
        edtEdit.setText(ten);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newND = edtEdit.getText().toString().trim();
                String newSt = edtEditS.getText().toString().trim();
                if(TextUtils.isEmpty(newND) && TextUtils.isEmpty(newSt)){
                    Toast.makeText(ListActivity.this, "Chưa nhập đủ nội dung!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                sqLite.QueryData("UPDATE Cities SET CityName = '"+ newND +"' WHERE Id = '"+ id +"'");
                sqLite.QueryData("UPDATE Cities SET CityState = '"+ newSt +"' WHERE Id = '"+ id +"'");
                dialog.dismiss();
                ShowDataCity();
            }
        });

        dialog.show();
    }

    public void DialogDeleteCity(String ten,int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn xóa " + ten + "?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sqLite.QueryData("DELETE FROM Cities WHERE Id = "+ id +"");
                ShowDataCity();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    public void DialogUpdateAttraction(String ten, String add, int idf, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_attraction_edit);

        EditText edtEdit = dialog.findViewById(R.id.edtAttractionName1);
        EditText edtEditS = dialog.findViewById(R.id.edtAttractionAddress1);
        EditText edtEditA = dialog.findViewById(R.id.edtAttractionIdf1);
        Button btnEdit = dialog.findViewById(R.id.btnEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        edtEdit.setText(ten);
        edtEditS.setText(add);
        edtEditA.setText(idf);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newND = edtEdit.getText().toString().trim();
                String newSt = edtEditS.getText().toString().trim();
                int newSr = Integer.parseInt(edtEditA.getText().toString().trim());
                if(TextUtils.isEmpty(newND) && TextUtils.isEmpty(newSt)){
                    Toast.makeText(ListActivity.this, "Chưa nhập đủ nội dung!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                sqLite.QueryData("UPDATE Attraction SET LocationName = '"+ newND +"' WHERE Id = '"+ id +"'");
                sqLite.QueryData("UPDATE Attraction SET LocationAddress = '"+ newSt +"' WHERE Id = '"+ id +"'");
                sqLite.QueryData("UPDATE Attraction SET Idf = "+ newSr +" WHERE Id = '"+ id +"'");
                dialog.dismiss();
                ShowDataAttraction();
            }
        });

        dialog.show();
    }

    public void DialogDeleteAttraction(String ten,int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn xóa " + ten + "?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sqLite.QueryData("DELETE FROM Attraction WHERE Id = '"+ id +"'");
                ShowDataAttraction();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    public void DialogUpdateAccount(String ten, String pass, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_city_edit);

        EditText edtEdit = dialog.findViewById(R.id.edtCityName);
        EditText edtEditS = dialog.findViewById(R.id.edtCityState);
        Button btnEdit = dialog.findViewById(R.id.btnCityEdit);
        Button btnCancel = dialog.findViewById(R.id.btnCityCancel);

        edtEditS.setText(pass);
        edtEdit.setText(ten);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newND = edtEdit.getText().toString().trim();
                String newSt = edtEditS.getText().toString().trim();
                if(TextUtils.isEmpty(newND) && TextUtils.isEmpty(newSt)){
                    Toast.makeText(ListActivity.this, "Chưa nhập nội dung!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                sqLite.QueryData("UPDATE Account SET AccountName = '"+ newND +"' WHERE Id = '"+ id +"'");
                sqLite.QueryData("UPDATE Account SET AccountPass = '"+ newSt +"' WHERE Id = '"+ id +"'");
                dialog.dismiss();
                ShowDataAccount();
            }
        });

        dialog.show();
    }

    public void DialogDeleteAccount(String ten,int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn xóa " + ten + "?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sqLite.QueryData("DELETE FROM Account WHERE Id = "+ id +"");
                ShowDataAccount();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }
}