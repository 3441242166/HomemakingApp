package com.example.wanhao.homemakingapp.fragment;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.base.LazyLoadFragment;

/**
 * Created by wanhao on 2018/2/27.
 */

public class OtherFragment extends LazyLoadFragment {



    @Override
    protected int setContentView() {
        return R.layout.framgnet_other;
    }

    @Override
    protected void lazyLoad() {

        initData();
        initView();
        initEvent();

    }

    private void initData() {

    }

    private void initView() {

    }

    private void initEvent() {

    }
}
