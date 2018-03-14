package com.example.wanhao.homemakingapp.view;

import com.example.wanhao.homemakingapp.base.IBaseView;
import com.example.wanhao.homemakingapp.bean.Course;

import java.util.List;

/**
 * Created by wanhao on 2018/2/23.
 */

public interface ICourseFgView extends IBaseView<List<Course>> {

    void deleteSucess();

}
