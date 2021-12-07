package com.uc.projectcourseapi.view.CourseView.AddCourse;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.uc.projectcourseapi.model.Course;
import com.uc.projectcourseapi.repositories.CourseRepository;

public class AddCourseViewModel extends AndroidViewModel {
    private CourseRepository courseRepository;
    private static final String TAG = "AddCourseViewModel";

    public AddCourseViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "token: "+token);
        courseRepository = CourseRepository.getInstance(token);
    }

    public MutableLiveData<Course.Courses> createCourse(Course.Courses courses){
        return courseRepository.createCourse(courses);
    }

    public MutableLiveData<Course.Courses> editCourse(String code, Course.Courses courses){
        return courseRepository.editCourse(code, courses);
    }
}
