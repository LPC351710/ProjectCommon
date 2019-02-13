package com.ppm.ppcomon.widget.actionbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppm.ppcomon.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BaseActionBar {

    private Context context;
    private Activity activity;
    private Fragment fragment;
    private View parent;

    private View vActionBar;
    private ImageView ivLeftIcon;
    private TextView tvTitle;
    private ImageView ivRightIcon;
    private TextView tvRightText;

    private int parentType;//1 activity, 2 fragment

    private BaseActionBarInterface mBaseActionBarInterface;

    public BaseActionBar(Activity activity) {
        super();
        this.activity = activity;
        context = activity;
        parent = activity.getWindow().peekDecorView();
        parentType = 1;
    }

    public BaseActionBar(Fragment fragment) {
        super();
        this.fragment = fragment;
        context = fragment.getContext();
        parent = fragment.getView();
        parentType = 2;
    }

    private void initView(final View parent) {
        vActionBar = parent.findViewById(R.id.action_bar);
        ivLeftIcon = parent.findViewById(R.id.left_icon);
        tvTitle = parent.findViewById(R.id.title);
        ivRightIcon = parent.findViewById(R.id.right_icon);
        tvRightText = parent.findViewById(R.id.right_text);

        ivLeftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activity instanceof BaseActionBarInterface) {
                    mBaseActionBarInterface = (BaseActionBarInterface) activity;
                    mBaseActionBarInterface.onLeftBtnClick();
                    return;
                }

                if (activity != null) {
                    activity.finish();
                } else if (fragment != null) {
                    FragmentManager fragmentManager = fragment.getFragmentManager();
                    if (fragmentManager == null) {
                        return;
                    }
                    fragmentManager.popBackStackImmediate();
                }
            }
        });
    }

    public void customNavigationBar(Drawable leftDrawable, Drawable rightDrawable, String title, String rightBtn) {

        if (ivLeftIcon == null) {
            initView(parent);
        }

        if (ivLeftIcon != null) {
            ivLeftIcon.setVisibility(View.VISIBLE);
            ivLeftIcon.setImageDrawable(leftDrawable);
        }

        if (tvTitle != null) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title == null ? "" : title);
        }

        if (ivRightIcon != null) {
            ivRightIcon.setVisibility(View.VISIBLE);
            ivRightIcon.setImageDrawable(rightDrawable);
        }

        if (tvRightText != null) {
            tvRightText.setVisibility(View.VISIBLE);
            tvRightText.setText(rightBtn == null ? "" : rightBtn);
        }
    }


    /**
     * 只有标题的导航栏
     *
     * @param title ac title
     */
    public void customNavigationBarWithTitle(String title) {
        customNavigationBar(null, null, title, null);
    }

    /**
     * 带有标题 返回按钮 右边文字操作按钮
     *
     * @param title    ac title
     * @param rightStr menu text
     */
    public void customNavigationBarWithRightStrBtn(String title, String rightStr) {
        Drawable backBtnDrawable = context.getResources().getDrawable(R.mipmap.nav_back);
        customNavigationBar(backBtnDrawable, null, title, rightStr);
    }

    /**
     * 带有标题和左边返回按钮的导航栏
     *
     * @param title ac title
     */
    public void customNavigationBarWithBackBtn(String title) {
        Drawable backBtnDrawable = context.getResources().getDrawable(R.mipmap.nav_back);
        customNavigationBar(backBtnDrawable, null, title, null);
    }

    /**
     * 带有标题 返回按钮 右边图标操作按钮
     *
     * @param title    ac title
     * @param rightImg drawable
     */
    public void customNavigationBarWithRightImgBtn(String title, Drawable rightImg) {
        Drawable backBtnDrawable = context.getResources().getDrawable(R.mipmap.nav_back);
        customNavigationBar(backBtnDrawable, rightImg, title, null);
    }

    public View getVActionBar() {
        return vActionBar;
    }

    public ImageView getIvLeftIcon() {
        return ivLeftIcon;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getIvRightIcon() {
        return ivRightIcon;
    }

    public TextView getTvRightText() {
        return tvRightText;
    }
}
