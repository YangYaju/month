package com.bwie.android_day20;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bwie.android_day20.carfragment.CarFragment;
import com.bwie.android_day20.clazzfragment.ClazzFragment;
import com.bwie.android_day20.homefragment.HomeFragment;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frameLayout;
    private TextView txtHome;
    private TextView txtClazz;
    private TextView txtCar;
    private HomeFragment homeFragment;
    private ClazzFragment clazzFragment;
    private CarFragment carFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initView();

        initData();

        setListener();
    }

    private void setListener() {
        txtHome.setOnClickListener(this);
        txtClazz.setOnClickListener(this);
        txtCar.setOnClickListener(this);
    }

    private void initData() {

        homeFragment = new HomeFragment();
        clazzFragment = new ClazzFragment();
        carFragment = new CarFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.frame_layout, homeFragment)
                .add(R.id.frame_layout, clazzFragment)
                .add(R.id.frame_layout, carFragment)
                .hide(clazzFragment)
                .hide(carFragment)
                .show(homeFragment)
                .commit();

    }

    private void initView() {
        frameLayout = findViewById(R.id.frame_layout);
        txtHome = findViewById(R.id.txt_home);
        txtClazz = findViewById(R.id.txt_clazz);
        txtCar = findViewById(R.id.txt_car);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.txt_home:
                transaction.show(homeFragment).hide(clazzFragment).hide(carFragment);
                txtHome.setTextColor(Color.RED);
                txtClazz.setTextColor(Color.BLACK);
                txtCar.setTextColor(Color.BLACK);
                break;
            case R.id.txt_clazz:
                transaction.hide(homeFragment).show(clazzFragment).hide(carFragment);
                txtHome.setTextColor(Color.BLACK);
                txtClazz.setTextColor(Color.RED);
                txtCar.setTextColor(Color.BLACK);
                break;
            case R.id.txt_car:
                transaction.hide(homeFragment).hide(clazzFragment).show(carFragment);
                txtHome.setTextColor(Color.BLACK);
                txtClazz.setTextColor(Color.BLACK);
                txtCar.setTextColor(Color.RED);
                break;
        }
        transaction.commit();
    }
}
