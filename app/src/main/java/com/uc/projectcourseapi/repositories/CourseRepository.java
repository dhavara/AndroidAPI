package com.uc.projectcourseapi.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.uc.projectcourseapi.model.Course;
import com.uc.projectcourseapi.retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository {
    private static CourseRepository courseRepository;
    private RetrofitService apiService;
    private static final String TAG = "courseRepository";

    private CourseRepository(String token) {
        Log.d(TAG, "token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static CourseRepository getInstance(String token) {
        if (courseRepository == null) {
            courseRepository = new CourseRepository(token);
        }
        return courseRepository;
    }

    public synchronized void resetInstances() {
        if (courseRepository != null) {
            courseRepository = null;
        } else {
            courseRepository = null;
        }
    }

    public MutableLiveData<Course> getCourses() {
        final MutableLiveData<Course> listCourses = new MutableLiveData<>();
        apiService.getCourses().enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getCourses().size());
                        listCourses.postValue(response.body());
                    }
                }
            }


            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCourses;
    }

    public MutableLiveData<Course> getCourseDetail(String code) {
        final MutableLiveData<Course> listCourseDetail = new MutableLiveData<>();
        apiService.getCourseDetail(code).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body());
                        listCourseDetail.postValue(response.body());
                    }
                }
            }


            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCourseDetail;
    }

    public MutableLiveData<Course.Courses> createCourse(Course.Courses courses) {
        final MutableLiveData<Course.Courses> listAddCourse = new MutableLiveData<>();
        apiService.createCourses(courses).enqueue(new Callback<Course.Courses>() {
            @Override
            public void onResponse(Call<Course.Courses> call, Response<Course.Courses> response) {
                Log.d(TAG, "onResponse: " + response.body());
                listAddCourse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Course.Courses> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


        return listAddCourse;
    }

    public MutableLiveData<Course.Courses> editCourse(String code, Course.Courses courses){
        final MutableLiveData<Course.Courses> listEditCourse = new MutableLiveData<>();
        apiService.editCourses(code, courses).enqueue(new Callback<Course.Courses>() {
            @Override
            public void onResponse(Call<Course.Courses> call, Response<Course.Courses> response) {
                Log.d(TAG, "onResponse: " + response.body());
                listEditCourse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Course.Courses> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listEditCourse;
    }

    public MutableLiveData<String> deleteCourses(String code){
        MutableLiveData<String> message = new MutableLiveData<>();
        apiService.deleteCourse(code).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()){
                    if (response.body() !=null){
                        try{
                            JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                            String msg = object.getString("message");
                            Log.d(TAG, "result: " + msg);
                            message.postValue(msg);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return message;
    }
}
