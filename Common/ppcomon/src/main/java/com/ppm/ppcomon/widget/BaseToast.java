package com.ppm.ppcomon.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.base.app.App;
import me.drakeet.support.toast.BadTokenListener;
import me.drakeet.support.toast.ToastCompat;


/**
 * @author lpc
 */
public class BaseToast {
    private static long TIME_LENGTH = (long) (2000 * 1.2);
    private static CharSequence mLastText;
    private static long mLastToastTime;

    public static void showLong(final CharSequence charSequence) {
        showLong(App.getContext(), charSequence);
    }

    public static void showLong(Context context, final CharSequence charSequence) {
        show(context, charSequence, false);
    }

    public static void showShort(final CharSequence charSequence) {
        showShort(App.getContext(), charSequence);
    }

    public static void showShort(Context context, final CharSequence charSequence) {
        show(context, charSequence, true);
    }

    private static void show(Context context, final CharSequence charSequence, final boolean
            isShort) {
        if (charSequence == null) {
            return;
        }

        if (!TextUtils.isEmpty(mLastText)
                && mLastText.equals(charSequence)
                && (System.currentTimeMillis() - mLastToastTime) < TIME_LENGTH) {
            return;
        }

        final View view = LayoutInflater.from(context).inflate(R.layout.toast, null, false);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        TextView contentV = (TextView) view.findViewById(R.id.toast_content);
        contentV.setText(charSequence);

        ToastCompat toast = ToastCompat.makeText(App.getContext(), charSequence, Toast
                .LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.setView(view);
        toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.setBadTokenListener(new BadTokenListener() {
            @Override
            public void onBadTokenCaught(@NonNull Toast toast) {
                toast = new Toast(App.getContext());
                toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
                toast.setView(view);
                toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
                toast.show();
            }
        });
        toast.show();

        mLastText = charSequence;
        mLastToastTime = System.currentTimeMillis();
    }

}
