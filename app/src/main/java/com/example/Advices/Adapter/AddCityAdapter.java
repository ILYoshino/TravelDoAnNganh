package com.example.Advices.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.Advices.Activity.AddCityActivity;
import com.example.Advices.Activity.AddCountryActivity;
import com.example.Advices.Activity.ListActivity;
import com.example.Advices.Oject.City;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;

import java.util.List;

public class AddCityAdapter extends BaseAdapter {
    private ListActivity context;
    private int layout;
    private List<City> cityList;

    public AddCityAdapter(ListActivity context, int layout, List<City> cityList) {
        this.context = context;
        this.layout = layout;
        this.cityList = cityList;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView nd, id;

        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AddCityAdapter.ViewHolder holder;
        if(view == null){
            holder = new AddCityAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.nd = view.findViewById(R.id.ndName);
            holder.id = view.findViewById(R.id.lvid);
            holder.imgEdit = view.findViewById(R.id.imgedit);
            holder.imgDelete = view.findViewById(R.id.imgdelete);
            view.setTag(holder);
        }else{
            holder = (AddCityAdapter.ViewHolder) view.getTag();
        }

        City city = cityList.get(i);
        holder.nd.setText(city.getNameCity());
        holder.id.setText(String.valueOf(city.getIdCity()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
                context.DialogUpdateCity(city.getNameCity(), city.getState(), city.getIdCity());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDeleteCity(city.getNameCity(), city.getIdCity());
            }
        });
        return view;
    }
}
