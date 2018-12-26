package com.ppm.ppcomon.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

/**
 * Created by yanweiqiang on 2017/11/14.
 */
public class ViewAttachment {
    private FrameLayout attachmentRoot;
    private View parent;
    private View child;

    public ViewAttachment(View child) {
        super();
        this.child = child;
    }

    public ViewAttachment(Context context, int layoutId) {
        super();
        this.child = LayoutInflater.from(context).inflate(layoutId, null);
    }

    public void attachTo(View view) {
        this.parent = view;

        ViewParent viewParent = view.getParent();
        ViewGroup parent = (ViewGroup) viewParent;

        int index = parent.indexOfChild(view);

        parent.removeView(view);

        attachmentRoot = new FrameLayout(view.getContext());
        attachmentRoot.setLayoutParams(view.getLayoutParams());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        attachmentRoot.addView(view);
        if (child != null) {
            child.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            attachmentRoot.addView(child);
        }
        parent.addView(attachmentRoot, index);
    }

    public void changeParent(View newParentView) {
        this.parent = newParentView;

        ViewParent viewParent = attachmentRoot.getParent();
        ViewGroup parent = (ViewGroup) viewParent;

        int index = parent.indexOfChild(attachmentRoot);

        View originView = parent.getChildAt(0);
        originView.setLayoutParams(attachmentRoot.getLayoutParams());

        attachmentRoot.removeView(originView);
        attachmentRoot.removeView(child);

        parent.removeView(attachmentRoot);
        parent.addView(originView, index);

        attachTo(newParentView);
    }

    public void changeChild(View newChildView) {
        if (child != null) {
            attachmentRoot.removeView(child);
        }
        attachmentRoot.addView(newChildView);
        child = newChildView;
        child.requestLayout();
    }

    public View getParent() {
        return parent;
    }

    public View getChild() {
        return child;
    }

    public void hide() {
        hide(android.R.anim.fade_out);
    }

    public void hide(int animRes) {
        if (child == null) {
            return;
        }

        child.setAnimation(AnimationUtils.loadAnimation(child.getContext(), animRes));
        child.setVisibility(View.GONE);
    }

    public void show() {
        show(android.R.anim.fade_in);
    }

    public void show(int animRes) {
        if (child == null) {
            return;
        }

        child.setAnimation(AnimationUtils.loadAnimation(child.getContext(), animRes));
        child.setVisibility(View.VISIBLE);
    }

    public boolean isShowing() {
        return child.getVisibility() == View.VISIBLE;
    }
}