package com.example.Advices.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Advices.Activity.ListActivity;
import com.example.Advices.Oject.Account;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;

import java.util.List;

public class AddAccountAdapter extends BaseAdapter {
    private ListActivity context;
    private int layout;
    private List<Account> accountList;

    public AddAccountAdapter(ListActivity context, int layout, List<Account> accountList) {
        this.context = context;
        this.layout = layout;
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList.size();
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
        TextView nd, pass, id;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AddAccountAdapter.ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.nd = view.findViewById(R.id.ndacc);
            holder.pass = view.findViewById(R.id.ndpass);
            holder.id = view.findViewById(R.id.lvid);
            holder.imgEdit = view.findViewById(R.id.imgedit1);
            holder.imgDelete = view.findViewById(R.id.imgdelete1);
            view.setTag(holder);
        }else{
            holder = (AddAccountAdapter.ViewHolder) view.getTag();
        }

        Account account = accountList.get(i);
        holder.nd.setText(account.getName());
        holder.pass.setText(account.getPass());
        holder.id.setText(String.valueOf(account.getId()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
                context.DialogUpdateAccount(account.getName(), account.getPass(), account.getId());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDeleteAccount(account.getName(), account.getId());
            }
        });

        return view;
    }
}
