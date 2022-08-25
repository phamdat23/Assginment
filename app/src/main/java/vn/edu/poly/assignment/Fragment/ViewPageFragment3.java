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
import vn.edu.poly.assignment.Adapter.ViewPageAdapter3;
import vn.edu.poly.assignment.Animation.DepthPageTransformer;
import vn.edu.poly.assignment.Animation.ZoomOutPageTransformer;
import vn.edu.poly.assignment.R;


public class ViewPageFragment3 extends Fragment {

    private TabLayout tapLayout3;
    private ViewPager2 viewPager3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_view_page3, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tapLayout3 = (TabLayout) view.findViewById(R.id.tap_layout3);
        viewPager3 =  view.findViewById(R.id.view_pager3);
        ViewPageAdapter3 viewPageAdapter3 = new ViewPageAdapter3(this);
        viewPager3.setAdapter(viewPageAdapter3);
        TabLayoutMediator mediator = new TabLayoutMediator(tapLayout3, viewPager3, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Thống kê thu");
                }else{
                    tab.setText("Thông kê chi");
                }
            }
        });
        mediator.attach();
        viewPager3.setPageTransformer(new ZoomOutPageTransformer());
        viewPager3.setPageTransformer(new DepthPageTransformer());
    }
}