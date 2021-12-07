package com.uc.projectcourseapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Course implements Parcelable {


    private List<Courses> courses;
    private String message;

    protected Course(Parcel in) {
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public static Course objectFromData(String str) {

        return new Gson().fromJson(str, Course.class);
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class Courses {
        private String course_code;
        private String course_name;
        private String lecturer;
        private int number_sks;
        private String description;

        public Courses(String course_code, String course_name, String lecturer, int number_sks, String description) {
            this.course_code = course_code;
            this.course_name = course_name;
            this.lecturer = lecturer;
            this.number_sks = number_sks;
            this.description = description;
        }

        public static Courses objectFromData(String str) {

            return new Gson().fromJson(str, Courses.class);
        }

        public String getCourse_code() {
            return course_code;
        }

        public void setCourse_code(String course_code) {
            this.course_code = course_code;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getLecturer() {
            return lecturer;
        }

        public void setLecturer(String lecturer) {
            this.lecturer = lecturer;
        }

        public int getNumber_sks() {
            return number_sks;
        }

        public void setNumber_sks(int number_sks) {
            this.number_sks = number_sks;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
