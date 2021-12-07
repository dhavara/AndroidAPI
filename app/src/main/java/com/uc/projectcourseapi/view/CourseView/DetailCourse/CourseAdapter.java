package com.uc.projectcourseapi.view.CourseView.DetailCourse;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.projectcourseapi.R;
import com.uc.projectcourseapi.model.Course;

import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CardViewViewHolder> {
    private Context context;
    private List<Course.Courses> coursesList;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    public List<Course.Courses> getCourseList() {
        return coursesList;
    }

    public void setCoursesList(List<Course.Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public CourseAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_course, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Course.Courses results = getCourseList().get(position);
        holder.course_code.setText(results.getCourse_code());
        holder.course_title.setText(results.getCourse_name());
        holder.course_lecturer.setText(results.getLecturer());
        holder.course_desc.setText(results.getDescription());
        holder.cardView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("course_code", results.getCourse_code().toLowerCase());
            Navigation.findNavController(view).navigate(R.id.action_courseFragment2_to_detailCourseFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView course_code, course_title, course_lecturer, course_desc;
        CardView cardView;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            course_code = itemView.findViewById(R.id.course_code);
            course_title = itemView.findViewById(R.id.course_title);
            course_lecturer = itemView.findViewById(R.id.course_lecturer);
            course_desc = itemView.findViewById(R.id.course_desc);
            cardView = itemView.findViewById(R.id.cv_layout_course);
        }
    }
}
