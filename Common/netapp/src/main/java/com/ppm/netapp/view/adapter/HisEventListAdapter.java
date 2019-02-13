package com.ppm.netapp.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppm.netapp.R;
import com.ppm.netapp.model.HisEvent;
import com.ppm.ppcomon.utils.ImageLoader;
import com.ppm.ppcomon.widget.adapter.BaseRVAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HisEventListAdapter extends BaseRVAdapter<HisEvent.ResultBean, HisEventListAdapter.HisViewHolder> {

    private Activity mActivity;

    public HisEventListAdapter(Activity activity) {
        mActivity = activity;
    }

    @NonNull
    @Override
    public HisViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HisViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_his_list, viewGroup, false));
    }

    @Override
    protected void onBindViewHolder(HisEvent.ResultBean event, HisViewHolder viewHolder, int i) {
        if (event != null && viewHolder != null) {
            ImageLoader.loadImage(mActivity, event.getPic(), viewHolder.imgEvent);
            viewHolder.txtTitle.setText(event.getTitle());
            String timeStr = event.getYear() + "-" + event.getMonth() + "-" + event.getDay() + " (" + event.getLunar() + ")";
            viewHolder.txtTime.setText(timeStr);
            viewHolder.txtDesc.setText(event.getDes());
        }
    }

    static class HisViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_event)
        ImageView imgEvent;

        @BindView(R.id.txt_title)
        TextView txtTitle;

        @BindView(R.id.txt_time)
        TextView txtTime;

        @BindView(R.id.txt_desc)
        TextView txtDesc;

        HisViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
