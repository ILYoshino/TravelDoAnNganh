package com.example.Advices.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Advices.Activity.AddAttractionActivity;
import com.example.Advices.Activity.AddCityActivity;
import com.example.Advices.Activity.ListActivity;
import com.example.Advices.Oject.Attraction;
import com.example.Advices.Oject.City;
import com.example.Advices.R;

import java.util.List;

public class AddAttractionAdapter extends BaseAdapter {
    private ListActivity context;
    private int layout;
    private List<Attraction> attractionList;

    public AddAttractionAdapter(ListActivity context, int layout, List<Attraction> attractionList) {
        this.context = context;
        this.layout = layout;
        this.attractionList = attractionList;
    }

    @Override
    public int getCount() {
        return attractionList.size();
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
        AddAttractionAdapter.ViewHolder holder;
        if(view == null){
            holder = new AddAttractionAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.nd = view.findViewById(R.id.ndName);
            holder.id = view.findViewById(R.id.lvid);
            holder.imgEdit = view.findViewById(R.id.imgedit);
            holder.imgDelete = view.findViewById(R.id.imgdelete);
            view.setTag(holder);
        }else{
            holder = (AddAttractionAdapter.ViewHolder) view.getTag();
        }

        Attraction attraction = attractionList.get(i);
        holder.nd.setText(attraction.getName());
        holder.id.setText(String.valueOf(attraction.getId()));

//        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
//                context.DialogUpdate(attraction.getName(), attraction.getAddress(), attraction.getIdf(), attraction.getId());
//            }
//        });
//
//        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.DialogDelete(attraction.getName(), attraction.getId());
//            }
//        });
        return view;
    }
}
