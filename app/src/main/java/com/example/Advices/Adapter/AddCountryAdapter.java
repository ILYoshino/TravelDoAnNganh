package com.example.Advices.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Advices.Activity.AddCountryActivity;
import com.example.Advices.Activity.ListActivity;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;

import java.util.List;

public class AddCountryAdapter extends BaseAdapter {
    private ListActivity context;
    private int layout;
    private List<Country> countryList;

    public AddCountryAdapter(ListActivity context, int layout, List<Country> countryList) {
        this.context = context;
        this.layout = layout;
        this.countryList = countryList;
    }

    @Override
    public int getCount() {
        return countryList.size();
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
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.nd = view.findViewById(R.id.ndName);
            holder.id = view.findViewById(R.id.lvid);
            holder.imgEdit = view.findViewById(R.id.imgedit);
            holder.imgDelete = view.findViewById(R.id.imgdelete);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Country country = countryList.get(i);
        holder.nd.setText(country.getNameCountry());
        holder.id.setText(String.valueOf(country.getIdcountry()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
                context.DialogUpdateCountry(country.getNameCountry(), country.getIdcountry());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDeleteCountry(country.getNameCountry(), country.getIdcountry());
            }
        });

        return view;
    }
}
