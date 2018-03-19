package com.example.wanhao.homemakingapp.service;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by wanhao on 2018/2/24.
 */

public interface AddCourseService {
    @POST("addIndent.do")
    Observable<Response<ResponseBody>> addCourse(@Body RequestBody body);

}
