package com.example.wanhao.homemakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.bean.Document;

import java.util.List;

/**
 * Created by wanhao on 2018/3/7.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.Holder> implements View.OnClickListener,View.OnLongClickListener{

    private List<Document> courseList;

    private OnItemClickListener onItemClickListener;
    private OnLongItemClickListener onItemLongClickListener;

    private View view;
    private Context context;

    public DocumentAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Document> courseList){
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    @Override
    public DocumentAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        Holder vh = new Holder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(DocumentAdapter.Holder holder, int position) {
        Document course = courseList.get(position);

        holder.name.setText(course.getTitle());
        holder.size.setText(course.getSize());
        holder.time.setText(course.getTime());
        //Glide.with(context).load(course.getImgUrl()+"?token="+ SaveDataUtil.getValueFromSharedPreferences(context, ApiConstant.USER_TOKEN)).into(holder.bck);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return courseList==null ? 0:courseList.size();
    }

    @Override
    public void onClick(View view) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(onItemLongClickListener!=null){
            onItemLongClickListener.onLongItemClick(view,(int)view.getTag());
        }
        return true;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnLongItemClickListener(OnLongItemClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public TextView size;
        public TextView name;
        public TextView time;
        public ImageView bck;

        public Holder(View view) {
            super(view);
            name = view.findViewById(R.id.item_document_name);
            size = view.findViewById(R.id.item_document_size);
            time = view.findViewById(R.id.item_document_time);
            bck = view.findViewById(R.id.item_document_image);
        }
    }

    public interface OnLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}