package com.example.Advices.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Advices.Activity.CityActivity;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    List<Country> countryList;
    private Context context;

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = countryList.get(position);
        int id = country.getIdcountry();
        String cname = country.getNameCountry();

        holder.countryname.setText(country.getNameCountry());
        Glide.with(context)
                .load(countryList.get(position).getImage())
                .into(holder.countryflag);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent i = new Intent(context, CityActivity.class);
                    i.putExtra("countryid",id);
                    i.putExtra("countryname", cname);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryname;
        ImageView countryflag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryname = itemView.findViewById(R.id.lvcountryname);
            countryflag = itemView.findViewById(R.id.imgcountrycard);
        }
    }
}