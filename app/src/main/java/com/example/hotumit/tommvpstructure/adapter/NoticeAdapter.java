package com.example.hotumit.tommvpstructure.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hotumit.tommvpstructure.R;
import com.example.hotumit.tommvpstructure.main_activity.RecyclerItemClickListener;
import com.example.hotumit.tommvpstructure.model.Notice;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemDao;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.EmployeeViewHolder> {

    private ArrayList<PhotoItemDao> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;


    public NoticeAdapter(ArrayList<PhotoItemDao> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNoticeTitle.setText(dataList.get(position).getUsername());
        holder.txtNoticeBrief.setText(dataList.get(position).getCaption());
        holder.txtNoticeFilePath.setText(dataList.get(position).getIso());
        Glide
                .with(holder.imageView.getContext())
                /*.load(dataList.get(position).getImageUrl().get(0))*/
                .load(dataList.get(position).getImageUrl())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtNoticeTitle, txtNoticeBrief, txtNoticeFilePath;
        ImageView imageView;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtNoticeTitle =  itemView.findViewById(R.id.txt_notice_title);
            txtNoticeBrief =  itemView.findViewById(R.id.txt_notice_brief);
            txtNoticeFilePath =  itemView.findViewById(R.id.txt_notice_file_path);
            imageView =  itemView.findViewById(R.id.imgview);

        }
    }
}