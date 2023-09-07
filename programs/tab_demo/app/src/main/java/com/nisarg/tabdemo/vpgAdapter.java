package com.nisarg.tabdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

@SuppressWarnings("deprecation")
public class vpgAdapter extends FragmentPagerAdapter {
    public vpgAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       if(position == 1)
       {
           return new chatFrag();
       } else if (position == 2) {
           return new StatusFrag();
       }else {
           return new ProfileFrag();
       }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
       if(position == 1){
           return "Chats";
       } else if (position == 2) {
           return "Status";
       }else {
           return "Profile";
       }
    }
}
