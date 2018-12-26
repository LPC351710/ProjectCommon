package com.ppm.ppcomon.utils;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 通用的改变view尺寸工具类
 */
public class ViewSizeHelper {

    private static ViewSizeHelper instance;

    private ViewSizeHelper() {
    }

    private static final String TAG = "ViewSizeHelper";

    public static ViewSizeHelper getInstance() {
        if (instance == null) {
            instance = new ViewSizeHelper();
        }
        return instance;
    }

    /**
     * 获取view宽度
     *
     * @param view
     * @return
     */
    public int getWidth(View view) {
        try {
            LayoutParams lp = view.getLayoutParams();
            return lp.width;
        } catch (Exception e) {
            try {
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                return rlp.width;
            } catch (Exception e1) {
                try {
                    LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) view
                            .getLayoutParams();
                    return llp.width;
                } catch (Exception e2) {
                    return -1;
                }
            }
        }
    }

    /**
     * 设置view宽度 单位px
     *
     * @param view
     * @param width
     * @return
     */
    public int setWidth(View view, int width) {
        try {
            LayoutParams lp = view.getLayoutParams();
            lp.width = width;
            return lp.width;
        } catch (Exception e) {
            try {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                lp.width = width;
                return lp.width;
            } catch (Exception e1) {
                try {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view
                            .getLayoutParams();
                    lp.width = width;
                    return lp.width;
                } catch (Exception e2) {
                    return -1;
                }
            }
        }
    }

    /**
     * 设置控件宽度，并根据比例设置高度
     *
     * @param view        待设置的控件
     * @param width       要设置的宽度
     * @param scaleWidth  比例参照宽度
     * @param scaleHeight 比例参照高度
     */
    public void setWidth(View view, int width, int scaleWidth, int scaleHeight) {
        int height = width * scaleHeight / scaleWidth;
        setWidth(view, width);
        setHeight(view, height);
    }

    /**
     * 获取view高度 单位px
     *
     * @return
     */
    public int getHeight(View view) {
        try {
            LayoutParams lp = view.getLayoutParams();
            return lp.height;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 设置view高度 ， 单位px
     *
     * @param view
     * @param height
     * @return
     */
    public int setHeight(View view, int height) {
        try {
            LayoutParams lp = view.getLayoutParams();
            lp.height = height;
            return lp.height;
        } catch (Exception e) {
            try {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view
                        .getLayoutParams();
                lp.height = height;
                return lp.height;
            } catch (Exception e2) {
                try {
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view
                            .getLayoutParams();
                    lp.height = height;
                    return lp.height;
                } catch (Exception e3) {
                }
            }
            return -1;
        }
    }

    public int setHeight(View view, float height) {
        try {
            LayoutParams lp = view.getLayoutParams();
            String heightStr = height + "";
            if (heightStr.indexOf(".") > -1) {
                heightStr = heightStr.substring(0, heightStr.indexOf("."));
            }
            lp.height = Integer.parseInt(heightStr);
            return lp.height;
        } catch (Exception e) {
            return -1;
        }
    }

    public void setSize(View view, float width, float height) {
        setSize(view, (int) width, (int) height);
    }

    /**
     * 设置view尺寸 单位px
     *
     * @param view
     * @param width
     * @param height
     */
    public void setSize(View view, int width, int height) {
        try {
            LayoutParams lp = view.getLayoutParams();
            lp.width = width;
            lp.height = height;
        } catch (Exception e) {
        }
    }

    public void margin(View view, int left, int top, int right, int bottom) {
        try {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view
                    .getLayoutParams();
            if (left == -1) {
                left = lp.leftMargin;
            }
            if (top == -1) {
                top = lp.topMargin;
            }
            if (right == -1) {
                right = lp.rightMargin;
            }
            if (bottom == -1) {
                bottom = lp.bottomMargin;
            }
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        } catch (Exception e) {
            try {
                RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                if (left == -1) {
                    left = lp2.leftMargin;
                }
                if (top == -1) {
                    top = lp2.topMargin;
                }
                if (right == -1) {
                    right = lp2.rightMargin;
                }
                if (bottom == -1) {
                    bottom = lp2.bottomMargin;
                }
                lp2.setMargins(left, top, right, bottom);
                view.setLayoutParams(lp2);
            } catch (Exception e1) {
            }
        }
    }

    public void marginTop(View view, int marginTop) {
        margin(view, -1, marginTop, -1, -1);
    }

    public void marginTop(View view, float marginTop) {
        margin(view, -1, (int) marginTop, -1, -1);
    }

    public void marginLeft(View view, int marginLeft) {
        margin(view, marginLeft, -1, -1, -1);
    }

    public void marginLeft(View view, float marginLeft) {
        margin(view, (int) marginLeft, -1, -1, -1);
    }

    public void marginRight(View view, int marginRight) {
        margin(view, -1, -1, marginRight, -1);
    }

    public void marginRight(View view, float marginRight) {
        margin(view, -1, -1, (int) marginRight, -1);
    }

    public void marginBottom(View view, int marginBottom) {
        margin(view, -1, -1, -1, marginBottom);
    }

    public void marginBottom(View view, float marginBottom) {
        margin(view, -1, -1, -1, (int) marginBottom);
    }

}
