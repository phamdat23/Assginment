package vn.edu.poly.assignment.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Chi;
import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Thu;
import vn.edu.poly.assignment.Fragment.Fragment_Loai_chi;
import vn.edu.poly.assignment.Fragment.Fragment_Loai_thu;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter2 extends FragmentStateAdapter {

    public ViewPageAdapter2(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_Khoan_Thu();
            case 1:
                return new Fragment_Loai_thu();
            default:
                return new Fragment_Khoan_Thu();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
