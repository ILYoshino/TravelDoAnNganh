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
import com.example.Advices.Activity.DetailActivity;
import com.example.Advices.Oject.Attraction;
import com.example.Advices.R;

import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.ViewHolder> {
    List<Attraction> attractionList;
    private Context context;

    public AttractionAdapter(List<Attraction> attractionList, Context context) {
        this.attractionList = attractionList;
        this.context = context;
    }

    @NonNull
    @Override
    public AttractionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttractionAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_attraction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attraction attraction = attractionList.get(position);
        int id = attraction.getId();
        String name = attraction.getName();
        String address = attraction.getAddress();
        String pic = attraction.getPic();
        String desc = attraction.getDetail();
        String lat = attraction.getLat();
        String Long = attraction.getLong();
        int idf = attraction.getIdf();

        Glide.with(context)
                .load(attractionList.get(position).getPic())
                .into(holder.img);
        holder.name.setText(name);
        holder.address.setText(address);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("attractionid", id);
                    i.putExtra("attractionname", name);
                    i.putExtra("attractionaddress", address);
                    i.putExtra("attractionimg", pic);
                    i.putExtra("attractiondesc", desc);
                    i.putExtra("attractionlat", lat);
                    i.putExtra("attractionlong", Long);
                    i.putExtra("attractionidf", idf);
                    context.startActivity(i);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, address;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.attractionnamecard);
            address = itemView.findViewById(R.id.attractionaddresscard);
            img = itemView.findViewById(R.id.imgattractioncard);
        }
    }
}
