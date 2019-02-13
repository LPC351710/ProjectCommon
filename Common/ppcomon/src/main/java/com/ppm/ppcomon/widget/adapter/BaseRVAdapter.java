package com.ppm.ppcomon.widget.adapter;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRVAdapter<T, V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    protected List<T> mList;

    protected abstract void onBindViewHolder(T t, V viewHolder, int position);

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(AdapterView<?> parent, View view, int position, T t);
    }

    public BaseRVAdapter() {
        super();
        mList = new ArrayList<>();
    }

    public void refresh(List<T> list) {
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void load(List<T> list) {
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull V viewHolder, int position) {
        T t = null;
        if (mList != null && mList.size() > position) {
            t = mList.get(position);
        }

        viewHolder.itemView.setOnClickListener(v -> {
            onItemHolderClick(viewHolder);
        });

        onBindViewHolder(t, viewHolder, position);
    }

    private void onItemHolderClick(V viewHolder) {
        int position = viewHolder.getAdapterPosition();
        T data = null;
        if (mList != null && mList.size() > position) {
            data = mList.get(viewHolder.getAdapterPosition());
        }

        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, viewHolder.itemView,
                    viewHolder.getAdapterPosition(), data);
        }
    }
}
