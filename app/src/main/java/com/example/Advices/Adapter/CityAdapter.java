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
import com.example.Advices.Activity.AttractionActivity;
import com.example.Advices.Oject.City;
import com.example.Advices.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private Context context;
    private List<City> cityList;

    public CityAdapter(List<City> cityList, Context context) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CityAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City city = cityList.get(position);
        int id = city.getIdCity();
        String name = city.getNameCity();
        String state = city.getState();


        holder.cityname.setText(city.getNameCity());
        holder.statename.setText(city.getState());
        Glide.with(context)
                .load(cityList.get(position).getImg())
                .into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent i = new Intent(context, AttractionActivity.class);
                    i.putExtra("cityid", id);
                    i.putExtra("cityname", name);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityname, statename;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.citynamecard);
            img = itemView.findViewById(R.id.imgcitycard);
            statename = itemView.findViewById(R.id.statenamecard);
        }
    }
}