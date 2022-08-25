package vn.edu.poly.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Chi;
import vn.edu.poly.assignment.Fragment.Fragment_Khoan_Thu;
import vn.edu.poly.assignment.Fragment.Fragment_Profile;
import vn.edu.poly.assignment.Fragment.Fragment_Thong_Ke;
import vn.edu.poly.assignment.Fragment.LoginFragment;
import vn.edu.poly.assignment.Fragment.SinginFragment;
import vn.edu.poly.assignment.Fragment.ViewPageFragment;
import vn.edu.poly.assignment.Fragment.ViewPageFragment2;
import vn.edu.poly.assignment.Fragment.ViewPageFragment3;

public class ManHinhChinhActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private NavigationView nvView;
    private FrameLayout fragmentContent;
    private TabLayout mtablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        nvView = (NavigationView) findViewById(R.id.nv_view);
        fragmentContent = (FrameLayout) findViewById(R.id.fragment_content);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        nvView.setNavigationItemSelectedListener(this);
        replace(new ViewPageFragment2());
        nvView.getMenu().findItem(R.id.item_khoan_thu).setChecked(true);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_khoan_chi:
                replace(new ViewPageFragment());
                nvView.getMenu().findItem(R.id.item_khoan_chi).setChecked(true);
                nvView.getMenu().findItem(R.id.item_khoan_thu).setChecked(false);
                nvView.getMenu().findItem(R.id.item_thongke).setChecked(false);
                nvView.getMenu().findItem(R.id.item_profile).setChecked(false);
                nvView.getMenu().findItem(R.id.item_exit).setChecked(false);

                break;
            case R.id.item_khoan_thu:
                replace(new ViewPageFragment2());
                nvView.getMenu().findItem(R.id.item_khoan_thu).setChecked(true);
                nvView.getMenu().findItem(R.id.item_khoan_chi).setChecked(false);
                nvView.getMenu().findItem(R.id.item_thongke).setChecked(false);
                nvView.getMenu().findItem(R.id.item_profile).setChecked(false);
                nvView.getMenu().findItem(R.id.item_exit).setChecked(false);

                break;
            case R.id.item_thongke:
                replace(new ViewPageFragment3());
                nvView.getMenu().findItem(R.id.item_thongke).setChecked(true);
                nvView.getMenu().findItem(R.id.item_khoan_chi).setChecked(false);
                nvView.getMenu().findItem(R.id.item_khoan_thu).setChecked(false);
                nvView.getMenu().findItem(R.id.item_profile).setChecked(false);
                nvView.getMenu().findItem(R.id.item_exit).setChecked(false);
                break;
            case R.id.item_profile:
                replace(new Fragment_Profile());
                nvView.getMenu().findItem(R.id.item_profile).setChecked(true);
                nvView.getMenu().findItem(R.id.item_khoan_chi).setChecked(false);
                nvView.getMenu().findItem(R.id.item_khoan_thu).setChecked(false);
                nvView.getMenu().findItem(R.id.item_thongke).setChecked(false);
                nvView.getMenu().findItem(R.id.item_exit).setChecked(false);
                break;
            case R.id.item_exit:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                nvView.getMenu().findItem(R.id.item_exit).setChecked(true);
                nvView.getMenu().findItem(R.id.item_profile).setChecked(false);
                nvView.getMenu().findItem(R.id.item_khoan_chi).setChecked(false);
                nvView.getMenu().findItem(R.id.item_khoan_thu).setChecked(false);
                nvView.getMenu().findItem(R.id.item_thongke).setChecked(false);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_content, fragment);
        transaction.commit();
    }

}