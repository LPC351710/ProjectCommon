package com.ppm.ppcomon.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(V viewHolder, int position) {
        T t = null;
        if (mList != null && mList.size() > position) {
            t = mList.get(position);
        }

        onBindViewHolder(t, viewHolder, position);
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemHolderClick();
                }
            });
        }

        void onItemHolderClick() {
            int position = getAdapterPosition();
            T data = null;
            if (mList != null && mList.size() > position) {
                data =  mList.get(getAdapterPosition());
            }

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(null, itemView,
                        getAdapterPosition(), data);
            } else {
                throw new IllegalStateException("Please call setOnItemClickListener method set the click event listeners");
            }
        }
    }
}
