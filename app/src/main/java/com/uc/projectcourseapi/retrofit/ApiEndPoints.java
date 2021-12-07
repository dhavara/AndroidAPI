package com.uc.projectcourseapi.retrofit;

import com.google.gson.JsonObject;
import com.uc.projectcourseapi.model.Course;
import com.uc.projectcourseapi.model.Profile;
import com.uc.projectcourseapi.model.Project;
import com.uc.projectcourseapi.model.RegisterResponse;
import com.uc.projectcourseapi.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("name") String name, @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation);

    @GET("courses")
    Call<Course> getCourses();

    @GET("courses/{course}")
    Call<Course> getCourseDetail(@Path("course") String code);

    @POST("courses")
    Call<Course.Courses> createCourses(@Body Course.Courses course);

    @PUT("courses/{course}")
    Call<Course.Courses> editCourses(@Path("course") String code, @Body Course.Courses course);

    @DELETE("courses/{course}")
    Call<Course> deleteCourses(@Path("course") String code);

    @GET("projects")
    Call<Project> getProjects();

    @GET("users")
    Call<Profile> getProfile();

    @POST("logout")
    Call<JsonObject> logout();
}
