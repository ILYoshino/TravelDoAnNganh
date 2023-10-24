package com.example.Advices.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Advices.Activity.DetailActivity;
import com.example.Advices.Activity.ListActivity;
import com.example.Advices.Oject.Comment;
import com.example.Advices.Oject.Country;
import com.example.Advices.R;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private DetailActivity context;
    private int layout;
    private List<Comment> commentList;

    public CommentAdapter(DetailActivity context, int layout, List<Comment> commentList) {
        this.context = context;
        this.layout = layout;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
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
        TextView name, desc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommentAdapter.ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.name = view.findViewById(R.id.commentName);
            holder.desc = view.findViewById(R.id.commentnd);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Comment comment = commentList.get(i);
        holder.name.setText(comment.getName());
        holder.desc.setText(comment.getComment());

        return view;
    }
}
