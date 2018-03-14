package com.example.wanhao.homemakingapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.presenter.RegisterPresenter;
import com.example.wanhao.homemakingapp.util.ActivityCollector;
import com.example.wanhao.homemakingapp.view.IRegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,IRegisterView {
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.ac_register_loding) Button btGo;
    @BindView(R.id.ac_register_fab)FloatingActionButton fab;
    @BindView(R.id.ac_register_count)EditText etUsername;
    @BindView(R.id.ac_register_password)EditText etPassword;
    @BindView(R.id.ac_register_code)EditText etCode;
    @BindView(R.id.ac_register_getcode)TextView tvCode;
    @BindView(R.id.ac_register_cardview)CardView cardView;

    RegisterPresenter registerPresenter;

    SweetAlertDialog pDialog;

    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvCode.setText(millisUntilFinished/1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvCode.setClickable(true);
            tvCode.setText("获取验证码");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        InitView();
        InitEvent();
    }

    private void InitView() {
        registerPresenter = new RegisterPresenter(this,this);

        btGo.setOnClickListener(this);
        fab.setOnClickListener(this);
        tvCode.setOnClickListener(this);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loding...");
        pDialog.setCancelable(false);
    }

    private void InitEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_register_loding:
                registerPresenter.register(etUsername.getText().toString()
                ,etPassword.getText().toString()
                ,etCode.getText().toString());
                break;
            case R.id.ac_register_getcode:
                registerPresenter.getVerificationCode(etUsername.getText().toString(), tvCode, timer);
                break;
            case R.id.ac_register_fab:
                onBackPressed();
                break;
        }
    }

    @Override
    public void showProgress() {
        pDialog.show();
    }

    @Override
    public void disimissProgress() {
        pDialog.hide();
    }

    @Override
    public void loadDataSuccess(Object tData) {
        Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadDataError(String throwable) {
        Toast.makeText(this,throwable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tokenError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        ActivityCollector.finishAll();
        Intent intent = new Intent(this, LodingActivity.class);
        startActivity(intent);
    }
}
