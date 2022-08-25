package vn.edu.poly.assignment.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.poly.assignment.Adapter.ViewPageAdapter2;
import vn.edu.poly.assignment.Animation.DepthPageTransformer;
import vn.edu.poly.assignment.Animation.ZoomOutPageTransformer;
import vn.edu.poly.assignment.R;


public class ViewPageFragment2 extends Fragment {

        private TabLayout tapLayout2;
        private ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_view_page2, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tapLayout2 =  view.findViewById(R.id.tap_layout2);
        viewPager2 =  view.findViewById(R.id.view_pager2);
        ViewPageAdapter2 viewPageAdapter2 = new ViewPageAdapter2(this);
        viewPager2.setAdapter(viewPageAdapter2);
        TabLayoutMediator mediator = new TabLayoutMediator(tapLayout2, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Khoản thu");
                }else{
                    tab.setText("Loại thu");
                }
            }
        });
        mediator.attach();
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.setPageTransformer(new DepthPageTransformer());
    }
}