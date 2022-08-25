package vn.edu.poly.assignment.Fragment;

import android.content.Context;
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

import vn.edu.poly.assignment.Adapter.ViewPageAdapter;
import vn.edu.poly.assignment.Animation.DepthPageTransformer;
import vn.edu.poly.assignment.Animation.ZoomOutPageTransformer;
import vn.edu.poly.assignment.R;

public class ViewPageFragment extends Fragment {

     TabLayout tapLayout;
     ViewPager2 viewPager;
     Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_page, container, false);

        context= getContext();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tapLayout = (TabLayout) view.findViewById(R.id.tap_layout);
        viewPager = view.findViewById(R.id.view_pager);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tapLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Khoản chi");
                }else{
                    tab.setText("loại chi");
                }
            }
        });
        mediator.attach();
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.setPageTransformer(new DepthPageTransformer());

    }
}