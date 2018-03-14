package com.example.wanhao.homemakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.presenter.SplashPresenter;
import com.example.wanhao.homemakingapp.view.ISplashView;


public class SplashActivity extends AppCompatActivity implements ISplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this,this);
        presenter.loding();
    }

    @Override
    public void goLoding() {
        startActivity(new Intent(this,LodingActivity.class));
        finish();
    }

    @Override
    public void goCourse() {
        startActivity(new Intent(this,CourseActivity.class));
        finish();
    }
}
