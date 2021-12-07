package com.uc.projectcourseapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Project implements Parcelable {
    private List<Projects> projects;

    protected Project(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public static Project objectFromData(String str) {

        return new Gson().fromJson(str, Project.class);
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    public static class Projects {
        private String code;
        private String project;
        private String semester;
        private String mata_kuliah;
        private String description;

        public static Projects objectFromData(String str) {

            return new Gson().fromJson(str, Projects.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getMata_kuliah() {
            return mata_kuliah;
        }

        public void setMata_kuliah(String mata_kuliah) {
            this.mata_kuliah = mata_kuliah;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
