package com.uc.projectcourseapi.view.CourseView.AddCourse;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.projectcourseapi.R;
import com.uc.projectcourseapi.helper.SharedPreferenceHelper;
import com.uc.projectcourseapi.model.Course;
import com.uc.projectcourseapi.view.CourseView.CourseViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {
    Toolbar toolbar;

    TextInputLayout courseCode, courseTitle, courseLecturer, courseSks, courseDesc;
    Button btn_submit_course;

    private AddCourseViewModel addCourseViewModel;
    private CourseViewModel courseViewModel;
    private SharedPreferenceHelper helper;
    private static final String TAG = "AddCourseFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseFragment newInstance(String param1, String param2) {
        AddCourseFragment fragment = new AddCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String pageCode = getArguments().getString("course_code");
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.WHITE);


        courseCode = view.findViewById(R.id.course_code_input);
        courseTitle = view.findViewById(R.id.course_title_input);
        courseLecturer = view.findViewById(R.id.course_lecturer_input);
        courseSks = view.findViewById(R.id.course_sks_input);
        courseDesc = view.findViewById(R.id.course_desc_input);
        btn_submit_course = view.findViewById(R.id.btn_submit_course);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        addCourseViewModel = new ViewModelProvider(getActivity()).get(AddCourseViewModel.class);
        addCourseViewModel.init(helper.getAccessToken());

        if (pageCode == null){
            toolbar.setTitle("Add Course");

            btn_submit_course.setOnClickListener(view1 -> {
                if (!courseCode.getEditText().getText().toString().isEmpty()
                        && !courseTitle.getEditText().getText().toString().isEmpty()
                        && !courseLecturer.getEditText().getText().toString().isEmpty()
                        && !courseSks.getEditText().getText().toString().isEmpty()
                        && !courseDesc.getEditText().getText().toString().isEmpty()){
                    String code = courseCode.getEditText().getText().toString().trim();
                    String title = courseTitle.getEditText().getText().toString().trim();
                    String lecturer = courseLecturer.getEditText().getText().toString().trim();
                    String sks = courseSks.getEditText().getText().toString().trim();
                    String desc = courseDesc.getEditText().getText().toString().trim();

                    Course.Courses courses = addCourseData(code, title, lecturer, sks, desc);
                    addCourseViewModel.createCourse(courses).observe(requireActivity(), courses1 -> {
                        if (courses1 != null){
                            NavDirections actions = AddCourseFragmentDirections.actionAddCourseFragmentToCourseFragment2();
                            Navigation.findNavController(view1).navigate(actions);
                            Toast.makeText(requireActivity(), "Add Course Success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(requireActivity(), "Add Course Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(requireActivity(), "All field must not empty", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            toolbar.setTitle("Edit Course");
            courseViewModel = new ViewModelProvider(getActivity()).get(CourseViewModel.class);
            courseViewModel.init(helper.getAccessToken());
            courseViewModel.getCourseDetail(pageCode);
            courseViewModel.getResultCourseDetail().observe(getActivity(), getCourseDetail);

            btn_submit_course.setOnClickListener(view12 -> {
                if (!courseCode.getEditText().getText().toString().isEmpty()
                        && !courseTitle.getEditText().getText().toString().isEmpty()
                        && !courseLecturer.getEditText().getText().toString().isEmpty()
                        && !courseSks.getEditText().getText().toString().isEmpty()
                        && !courseDesc.getEditText().getText().toString().isEmpty()) {
                    String code = courseCode.getEditText().getText().toString().trim();
                    String title = courseTitle.getEditText().getText().toString().trim();
                    String lecturer = courseLecturer.getEditText().getText().toString().trim();
                    String sks = courseSks.getEditText().getText().toString().trim();
                    String desc = courseDesc.getEditText().getText().toString().trim();
                    Course.Courses courses = addCourseData(code, title, lecturer, sks, desc);
                    addCourseViewModel.editCourse(pageCode, courses).observe(requireActivity(), courses1 -> {
                        if (courses1 != null){
                            NavDirections actions = AddCourseFragmentDirections.actionAddCourseFragmentToCourseFragment2();
                            Navigation.findNavController(view12).navigate(actions);
                            Toast.makeText(requireActivity(), "Edit Course Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(requireActivity(), "Edit Course Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(requireActivity(), "All field must not empty", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private Course.Courses addCourseData(String code, String title, String lecturer, String sks, String desc) {
        Course.Courses courses = new Course.Courses(code, title, lecturer, Integer.parseInt(sks), desc);
        return courses;
    }

    private Observer<Course> getCourseDetail = new Observer<Course>() {
        @Override
        public void onChanged(Course course) {
            Course.Courses resultDetail = course.getCourses().get(0);
            if (course == null){
                courseCode.getEditText().setText("Unknown");
                courseTitle.getEditText().setText("Unknown");
                courseLecturer.getEditText().setText("Unknown");
                courseSks.getEditText().setText("Unknown");
                courseDesc.getEditText().setText("Unknown");
            }else{
                String codeCourse = resultDetail.getCourse_code();
                String courseName = resultDetail.getCourse_name();
                String lecturer = resultDetail.getLecturer();
                int sks = resultDetail.getNumber_sks();
                String desc = resultDetail.getDescription();
                courseCode.getEditText().setText(codeCourse);
                courseTitle.getEditText().setText(courseName);
                courseLecturer.getEditText().setText(lecturer);
                courseSks.getEditText().setText(String.valueOf(sks));
                courseDesc.getEditText().setText(desc);
            }
        }
    };
}
