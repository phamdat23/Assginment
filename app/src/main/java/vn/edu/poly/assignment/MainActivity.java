package vn.edu.poly.assignment;


import android.os.Bundle;
import android.widget.FrameLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.poly.assignment.Fragment.LoginFragment;
import vn.edu.poly.assignment.Fragment.SinginFragment;


public class MainActivity extends AppCompatActivity {
    public FrameLayout fragmentContent1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentContent1 = findViewById(R.id.fragment_content1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content1, new LoginFragment());
        transaction.replace(R.id.fragment_content1, new LoginFragment());
        transaction.commit();
    }

}