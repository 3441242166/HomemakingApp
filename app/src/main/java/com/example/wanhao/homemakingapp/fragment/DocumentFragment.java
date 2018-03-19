package com.example.wanhao.homemakingapp.fragment;

import com.example.wanhao.homemakingapp.R;
import com.example.wanhao.homemakingapp.base.LazyLoadFragment;

/**
 * Created by wanhao on 2018/2/27.
 */

public class DocumentFragment extends LazyLoadFragment {
    private static final String TAG = "DocumentFragment";


    @Override
    protected int setContentView() {
        return R.layout.framgnet_document;
    }

    @Override
    protected void lazyLoad() {
        initView();
    }

    private void initView() {

    }
}
