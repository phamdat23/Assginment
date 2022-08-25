package vn.edu.poly.assignment.Adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Chi;
import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Thu;
import vn.edu.poly.assignment.Fragment.Fragment_Loai_chi;
import vn.edu.poly.assignment.Fragment.Fragment_Loai_thu;

public class ViewPageAdapter extends FragmentStateAdapter {


    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment_Khoan_Chi();
            case 1:
                return new Fragment_Loai_chi();
            default:
                return new Fragment_Khoan_Chi();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
