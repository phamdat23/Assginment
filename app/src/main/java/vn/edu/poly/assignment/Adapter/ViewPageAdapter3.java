package vn.edu.poly.assignment.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Thu;
import vn.edu.poly.assignment.Fragment.Fragment_Loai_thu;
import vn.edu.poly.assignment.Fragment.Thong_Ke_ChiFragment;
import vn.edu.poly.assignment.Fragment.Thong_Ke_ThuFragment;

public class ViewPageAdapter3 extends FragmentStateAdapter {

    public ViewPageAdapter3(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Thong_Ke_ThuFragment();
            case 1:
                return new Thong_Ke_ChiFragment();
            default:
                return new Thong_Ke_ThuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

