package com.example.jun.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jun.MainActivity;
import com.example.jun.R;
import com.example.jun.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
  Context context;
  ArrayList<LoginBean.ResultBean> beans =   new ArrayList<LoginBean.ResultBean>();

    public MyAdapter(Context context)
    {
        this.context = context;
    }
    public void setData(List<LoginBean.ResultBean> list){
        if (list!=null){
            beans.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(beans.get(position).getCommodityName());
        Glide.with(context)
                .load(beans.get(position).getMasterPic())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
           name = itemView.findViewById(R.id.name);
        }
    }
}
