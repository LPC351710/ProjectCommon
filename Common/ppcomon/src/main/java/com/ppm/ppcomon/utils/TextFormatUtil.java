package com.ppm.ppcomon.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StrikethroughSpan;
import com.ppm.ppcomon.R;

import java.text.MessageFormat;

/**
 * yanweiqiang
 * 2018/1/26.
 */

public class TextFormatUtil {

    public static SpannableString formatPrice(String price) {
        return formatPrice(price, AppTools.getDimensionPixelSize(R.dimen.super_small_font_size));
    }

    public static SpannableString formatPrice(String price, int unitSize) {
        SpannableString ss = new SpannableString(MessageFormat.format("{0}{1}", AppTools.getString(R.string.unit_money), price));
        ss.setSpan(new AbsoluteSizeSpan(unitSize), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (ss.toString().contains(".")) {
            ss.setSpan(new AbsoluteSizeSpan(unitSize), ss.toString().indexOf("."), ss.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    public static SpannableString formatOldPrice(String price) {
        SpannableString ss = new SpannableString(MessageFormat.format("{0}{1}", AppTools.getString(R.string.unit_money), price));
        ss.setSpan(new AbsoluteSizeSpan(AppTools.getDimensionPixelSize(R.dimen.super_small_font_size)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StrikethroughSpan(), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
