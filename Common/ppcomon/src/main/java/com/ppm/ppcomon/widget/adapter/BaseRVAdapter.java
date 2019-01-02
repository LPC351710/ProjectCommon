package com.ppm.ppcomon.widget.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRVAdapter<T, V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    protected List<T> mList;

    protected abstract void onBindViewHolder(T t, V viewHolder, int position);

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
    public void onBindViewHolder(V viewHolder, int position) {
        T t = null;
        if (mList != null && mList.size() > position) {
            t = mList.get(position);
        }

        onBindViewHolder(t, viewHolder, position);
    }
}
