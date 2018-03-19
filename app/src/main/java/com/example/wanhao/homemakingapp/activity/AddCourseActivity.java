package com.example.wanhao.homemakingapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.base.TopBarBaseActivity;
import com.example.wanhao.homemakingapp.config.ApiConstant;
import com.example.wanhao.homemakingapp.presenter.AddCoursePresenter;
import com.example.wanhao.homemakingapp.util.ActivityCollector;
import com.example.wanhao.homemakingapp.view.IAddCourseView;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddCourseActivity extends TopBarBaseActivity implements View.OnClickListener, IAddCourseView {

    AddCoursePresenter mPresenter;

    @BindView(R.id.ac_choose_add)
    Button abtAdd;
    @BindView(R.id.ac_choose_name)
    EditText editText;
    @BindView(R.id.ac_choose_contant)
    EditText editTextContant;

    private SweetAlertDialog pDialog;
    private boolean isSucess = false;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ac_choose_add:
                mPresenter.add(editText.getText().toString(),editTextContant.getText().toString());
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_course;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("添加中...");
        pDialog.setCancelable(false);

        mPresenter = new AddCoursePresenter(this,this);

        abtAdd.setOnClickListener(this);

        setTopLeftButton(new OnClickListener() {
            @Override
            public void onClick() {
                AddCourseActivity.this.finish();
            }
        });

        setTitle("添加课程");
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
        Toast.makeText(this,"success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("result", ApiConstant.ADD_SUCCESS);
        setResult(ApiConstant.ADD_SUCCESS, intent);
        finish();
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
