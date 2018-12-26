package com.ppm.ppcomon.widget.refresh;

import android.content.Context;
import android.util.AttributeSet;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.widget.IRrdRefreshHeaderAndFooter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


public class SmartRefreshLayoutWrapper extends SmartRefreshLayout {
    public SmartRefreshLayoutWrapper(Context context) {
        super(context);
    }

    public SmartRefreshLayoutWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRefreshLayoutWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IRrdRefreshHeaderAndFooter getRrdRefreshHeader() {
        return (IRrdRefreshHeaderAndFooter) getRefreshHeader();
    }

    public IRrdRefreshHeaderAndFooter getRrdRefreshFooter() {
        return (IRrdRefreshHeaderAndFooter) getRefreshFooter();
    }

    public void useWhiteStyle() {
        useRefreshStyle(R.drawable.bg_main_gradient, R.mipmap.refresh_logo_white, getResources().getColor(R.color.white), getResources().getColor(R.color.white));
    }

    public void useGrayStyle() {
        useRefreshStyle(R.color.transparent, R.mipmap.refresh_logo_gray, getResources().getColor(R.color.light_gray), getResources().getColor(R.color.light_gray));
    }

    public void useRefreshStyle(final int bgResId, final int logoResId, final int progressColor, final int textColor) {
        post(new Runnable() {
            @Override
            public void run() {
                IRrdRefreshHeaderAndFooter headerAndFooter = null;

                if (getRefreshHeader() instanceof IRrdRefreshHeaderAndFooter) {
                    headerAndFooter = (IRrdRefreshHeaderAndFooter) getRefreshHeader();
                } else if (getRefreshFooter() instanceof IRrdRefreshHeaderAndFooter) {
                    headerAndFooter = (IRrdRefreshHeaderAndFooter) getRefreshFooter();
                }

                if (headerAndFooter != null) {
                    headerAndFooter.setBackgroundResource(bgResId);
                    headerAndFooter.setLogoRes(logoResId);
                    headerAndFooter.setProgressColor(progressColor);
                    headerAndFooter.setTextColor(textColor);
                }
            }
        });
    }

    public void useWhiteStyleTransparent() {
        useRefreshAndLoadStyle(R.color.transparent, R.mipmap.refresh_logo_white, getResources().getColor(R.color.white), getResources().getColor(R.color.white));
    }

    public void useRefreshAndLoadStyle(final int bgResId, final int logoResId, final int progressColor, final int textColor) {
        post(new Runnable() {
            @Override
            public void run() {
                IRrdRefreshHeaderAndFooter headerAndFooter = null;

                if (getRefreshHeader() instanceof IRrdRefreshHeaderAndFooter) {
                    headerAndFooter = (IRrdRefreshHeaderAndFooter) getRefreshHeader();
                    if (headerAndFooter != null) {
                        headerAndFooter.setBackgroundResource(bgResId);
                        headerAndFooter.setLogoRes(logoResId);
                        headerAndFooter.setProgressColor(progressColor);
                        headerAndFooter.setTextColor(textColor);
                    }
                }

                if (getRefreshFooter() instanceof IRrdRefreshHeaderAndFooter) {
                    headerAndFooter = (IRrdRefreshHeaderAndFooter) getRefreshFooter();
                    if (headerAndFooter != null) {
                        headerAndFooter.setBackgroundResource(bgResId);
                        headerAndFooter.setLogoRes(logoResId);
                        headerAndFooter.setProgressColor(progressColor);
                        headerAndFooter.setTextColor(textColor);
                    }
                }
            }
        });
    }
}
