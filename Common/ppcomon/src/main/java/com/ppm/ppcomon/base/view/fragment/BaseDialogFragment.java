package com.ppm.ppcomon.base.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ppm.ppcomon.base.presenter.BasePresenter;
import com.ppm.ppcomon.base.view.intf.IBaseView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment<V extends IBaseView, T extends BasePresenter<V>> extends
        BaseFragment implements IBaseView {

    private Unbinder unbinder;

    public interface OnConfirmListener {
        void onConfirm(Object params);
    }

    protected OnConfirmListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        LayoutInflater in = LayoutInflater.from(getActivity());
        View pwdView = in.inflate(getLayoutId(), null);
        dialog.setContentView(pwdView);
        unbinder = ButterKnife.bind(this, pwdView);
        initView();
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConfirmListener) {
            listener = (OnConfirmListener) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics dm = new DisplayMetrics();
        if (getActivity() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        }

        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout((int) (dm.widthPixels * 0.85f), ViewGroup.LayoutParams
                    .WRAP_CONTENT);
        }
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
