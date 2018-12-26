package com.ppm.ppcomon.widget.window;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.base.app.App;
import com.ppm.ppcomon.widget.flowlayout.FlowLayout;
import com.ppm.ppcomon.widget.flowlayout.TagAdapter;

import java.util.List;

/**
 * @author by lpc on 2018/1/18.
 */
public class FlowTagAdapter extends TagAdapter<TypeBean> {

    private int currentPosition = 0;

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(int selectPosition);
    }

    public FlowTagAdapter(List<TypeBean> datas) {
        super(datas);
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataChanged();
    }

    @Override
    public View getView(FlowLayout parent, final int position, TypeBean typeBean) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TextView tv = (TextView) layoutInflater.inflate(R.layout.item_tag, null, false);
        changeState(position, tv);
        tv.setText(typeBean.getName());
        return tv;
    }

    @Override
    public void onSelected(int position, View view) {
        super.onSelected(position, view);
        currentPosition = position;
        notifyDataChanged();

        if (listener != null) {
            listener.onItemSelected(position);
        }
    }

    private void changeState(int position, View view) {
        if (position == currentPosition) {
            view.setBackgroundDrawable(App.getContext().getResources().getDrawable(R.drawable.bg_tag_selected));
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextColor(App.getContext().getResources().getColor(R.color.red));
            }
        } else {
            view.setBackgroundDrawable(App.getContext().getResources().getDrawable(R.drawable.bg_tag_unselected));
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextColor(App.getContext().getResources().getColor(R.color.light_gray));
            }
        }
    }
}
