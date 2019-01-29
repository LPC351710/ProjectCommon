package com.ppm.ppcomon.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import com.ppm.ppcomon.R;


/**
 * 自带删除功能的编辑框
 */
public class ClearEditText extends AppCompatEditText implements OnFocusChangeListener, TextWatcher {
    private Drawable mClearDrawable;
    private boolean isChange = true;
    private boolean isCancle = true;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.ic_edit_del);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        //setHintTextColor(0xFFffffff);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean touchable = event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    public void setClearIconVisible(boolean visible) {
        Drawable right = visible && isCancle ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        setClearIconVisible(s.length() > 0);
        if (null != mTextWatcher) {
            mTextWatcher.onTextChanged(s, start, count, after);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (null != mTextWatcher) {
            mTextWatcher.beforeTextChanged(s, start, count, after);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
//        if (isChange) {
//            setTextSize(PhoneModelManager.sp2px(getContext(), getText().length() == 0 ? R.dimen.sp_16 : R.dimen.sp_18));
//        }

        if (null != mTextWatcher) {
            mTextWatcher.afterTextChanged(s);
        }
    }

    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    public void doShakeAnimation() {
        this.startAnimation(shakeAnimation(5));
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean isChange) {
        this.isChange = isChange;
    }

    private ClearEditTextWatcher mTextWatcher;

    public void addClearEditTextWatcher(ClearEditTextWatcher textWatcher) {
        mTextWatcher = textWatcher;
    }

    public void removeClearEditTextWatcher() {
        mTextWatcher = null;
    }

    //是否完全取消X
    public void whetherCancleDelete(boolean isCancle) {
        this.isCancle = isCancle;

    }

    public interface ClearEditTextWatcher {
        public void beforeTextChanged(CharSequence s, int start, int count, int after);

        public void onTextChanged(CharSequence s, int start, int count, int after);

        public void afterTextChanged(Editable s);
    }

}
