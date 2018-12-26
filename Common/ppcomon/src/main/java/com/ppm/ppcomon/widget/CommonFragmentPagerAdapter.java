package com.ppm.ppcomon.widget;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * yanweiqiang
 * 2018/1/16.
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<CharSequence> charSequenceList;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<CharSequence> charSequenceList) {
        super(fm);

        if (fragmentList.size() != charSequenceList.size()) {
            throw new IllegalStateException("Fragment size != Title size.");
        }

        this.fragmentList = fragmentList;
        this.charSequenceList = charSequenceList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return charSequenceList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
