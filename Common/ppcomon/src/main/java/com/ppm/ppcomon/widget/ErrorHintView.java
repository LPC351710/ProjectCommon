package com.ppm.ppcomon.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.R2;
import com.ppm.ppcomon.constant.CfgConstant;
import com.ppm.ppcomon.constant.ErrorCodeConstant;
import com.ppm.ppcomon.utils.LogUtils;
import com.ppm.ppcomon.utils.StringUtil;

/**
 * Created by shy on 2017/9/18 0018.
 */
public class ErrorHintView {
    private final static String TAG = ErrorHintView.class.getSimpleName();

    @BindView(R2.id.iv_imageView_icon)
    ImageView mErrorHintImgView;
    @BindView(R2.id.tv_error_message)
    TextView mErrorHintTextView;
    @BindView(R2.id.cl_error_hint)
    View mErrorHintRelativeLayout;

    @OnClick(R2.id.iv_imageView_icon)
    public void onViewClicked(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        if (this.mHandler != null) {
            mHandler.sendEmptyMessage(CfgConstant.O2O_HANDLER_MSG_WHAT_ERROR_HINT_CLICK);
        }
    }

    private Context mContext;
    private int mHintImgResId;
    private String mHintMsg;
    private View mErrorHintView;
    private Handler mHandler;
    private FrameLayout.LayoutParams mLayoutParams;
    private View.OnClickListener onClickListener;

    public ErrorHintView(Context context, Handler handler, int hintImgResId, String hintMsg) {
        this.mContext = context;
        this.mHandler = handler;
        this.mHintImgResId = hintImgResId;
        this.mHintMsg = hintMsg;
        init();
    }

    @SuppressLint("InflateParams")
    private void init() {
        mErrorHintView = LayoutInflater.from(mContext).inflate(R.layout.common_error_hint, null);
        ButterKnife.bind(this, mErrorHintView);

        Drawable hintDrawable = this.mContext.getResources().getDrawable(this.mHintImgResId);

        if (null != hintDrawable) {
            this.mErrorHintImgView.setImageDrawable(hintDrawable);
        } else {
            this.mErrorHintImgView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap
                    .default_list));
        }

        if (StringUtil.isNotEmpty(this.mHintMsg)) {
            this.mErrorHintTextView.setText(this.mHintMsg);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void showErrorHintView(int imgResId, String hintMsg) {
        this.mHintImgResId = imgResId;
        this.mHintMsg = hintMsg;
        showErrorHintView(ErrorCodeConstant.ERR_CODE_CUSTOM);
    }

    public void showErrorHintView(View parentView, int imgResId, String hintMsg) {
        this.mHintImgResId = imgResId;
        this.mHintMsg = hintMsg;
        showErrorHintView(parentView, ErrorCodeConstant.ERR_CODE_CUSTOM);
    }


    public void showErrorHintView(FrameLayout.LayoutParams layoutParams, View parentView, int
            errorCode) {
        mLayoutParams = layoutParams;
        showErrorHintView(parentView, errorCode);
    }

    public void showErrorHintView(View parentView, int errorCode) {
        if (this.mErrorHintView == null) {
            return;
        }

        setErrorImgByErrorCode(errorCode);

        if (parentView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parentView;
            if (mErrorHintView.getParent() == null) {
                if (mLayoutParams == null) {
                    mLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
                            .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    mLayoutParams.topMargin = (int) mContext.getResources().getDimension(R.dimen
                            .action_bar_height);
                }
                viewGroup.addView(this.mErrorHintView, viewGroup.getChildCount(), mLayoutParams);
            }
        } else {
            LogUtils.e(TAG, "parent view not view group .");
            return;
        }

        this.mErrorHintView.setVisibility(View.VISIBLE);
        this.mErrorHintView.bringToFront();
    }

    public void showErrorHintView(FrameLayout.LayoutParams layoutParams, int errorCode) {
        mLayoutParams = layoutParams;
        showErrorHintView(errorCode);
    }

    public void showErrorHintView(int errorCode) {
        if (null == mErrorHintView) {
            return;
        }

        setErrorImgByErrorCode(errorCode);

        if (null == mErrorHintView.getParent()) {
            try {
                if (mLayoutParams == null) {
                    mLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
                            .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    mLayoutParams.topMargin = (int) mContext.getResources().getDimension(R.dimen
                            .action_bar_height);
                }

                ((Activity) mContext).addContentView(mErrorHintView, mLayoutParams);
            } catch (Exception e) {
                LogUtils.e("context is not activity. exception: " + e.getLocalizedMessage());
            }
        }

        this.mErrorHintView.setVisibility(View.VISIBLE);
        this.mErrorHintView.bringToFront();
    }

    private void setErrorImgByErrorCode(int errorCode) {
        Drawable hintDrawable;

        switch (errorCode) {
            case ErrorCodeConstant.ERR_CODE_NO_DATA:
                hintDrawable = this.mContext.getResources().getDrawable(R.mipmap.ic_no_data);
                this.mErrorHintImgView.setImageDrawable(hintDrawable);
                if (StringUtil.isNotEmpty(mHintMsg)) {
                    this.mErrorHintTextView.setText(mHintMsg);
                } else {
                    this.mErrorHintTextView.setText("暂无相关数据");
                }
                break;
            case ErrorCodeConstant.ERR_CODE_HTTP_RESPONSE_ERROR:
                hintDrawable = this.mContext.getResources().getDrawable(R.mipmap.ic_net_error);
                this.mErrorHintImgView.setImageDrawable(hintDrawable);
                this.mErrorHintTextView.setText("啊哦，网络不太顺畅哦");
                break;
            case ErrorCodeConstant.ERR_CODE_NO_NET:
                hintDrawable = this.mContext.getResources().getDrawable(R.mipmap.ic_default_error);
                this.mErrorHintImgView.setImageDrawable(hintDrawable);
                this.mErrorHintTextView.setText("没网的日子，生活索然无味");
                break;
            default:
                hintDrawable = this.mContext.getResources().getDrawable(mHintImgResId);
                this.mErrorHintImgView.setImageDrawable(hintDrawable);
                this.mErrorHintTextView.setText(mHintMsg);
                break;
        }
    }

    public void hiddenErrorHintView() {
        if (this.mErrorHintView == null) {
            return;
        }

        this.mErrorHintView.setVisibility(View.GONE);
    }

    public void updateViewData(String msg, Drawable errorHint) {
        if (!StringUtil.isEmpty(msg)) {
            this.mErrorHintTextView.setText(msg);
        }

        if (errorHint != null) {
            this.mErrorHintImgView.setImageDrawable(errorHint);
        }
    }

    public void disableBlankBodyClicked(boolean clicked) {
        if (this.mErrorHintRelativeLayout == null) {
            return;
        }

        if (clicked) {
            this.mErrorHintRelativeLayout.setOnClickListener(null);
        } else {
            this.mErrorHintRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

}
