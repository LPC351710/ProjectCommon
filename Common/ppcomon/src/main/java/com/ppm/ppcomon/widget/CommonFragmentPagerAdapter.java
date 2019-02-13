package com.ppm.ppcomon.widget;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
