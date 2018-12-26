package com.ppm.ppcomon.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ppm.ppcomon.R;


/**
 * Author yanweiqiang.
 * Date 2017/11/6.
 */

public class RadioIndicator extends android.support.v7.widget.AppCompatRadioButton {
    private int stroke;
    private Paint paint;

    public RadioIndicator(Context context) {
        super(context);
        init();
    }

    public RadioIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        stroke = (int) (2 * getResources().getDisplayMetrics().density);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(stroke);
        paint.setColor(getResources().getColor(R.color.red));
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        refreshRadioGroup();
    }

    private void refreshRadioGroup() {
        RadioGroup parent = ((RadioGroup) getParent());
        if (parent == null) {
            return;
        }
        for (int i = 0; i < parent.getChildCount(); i++) {
            RadioButton button = (RadioButton) parent.getChildAt(i);
            button.invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isChecked()) {
            //float tw = getPaint().measureText(getText().toString());
            //canvas.drawLine((getMeasuredWidth() - tw) / 2, getMeasuredHeight() - stroke, (getMeasuredWidth() + tw) / 2, getMeasuredHeight() - stroke, paint);
            canvas.drawLine(0, getMeasuredHeight() - stroke, getMeasuredWidth(), getMeasuredHeight() - stroke, paint);
        }
        super.onDraw(canvas);
    }
}
