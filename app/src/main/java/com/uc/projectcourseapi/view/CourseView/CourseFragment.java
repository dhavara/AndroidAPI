package com.uc.projectcourseapi.view.CourseView;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.projectcourseapi.R;
import com.uc.projectcourseapi.helper.SharedPreferenceHelper;
import com.uc.projectcourseapi.model.Course;
import com.uc.projectcourseapi.view.CourseView.DetailCourse.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    Toolbar toolbar;

    private CourseViewModel courseViewModel;
    private CourseAdapter courseAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private FloatingActionButton button_add_course;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Courses");
        toolbar.setTitleTextColor(Color.WHITE);

        button_add_course = view.findViewById(R.id.button_add_course);
        button_add_course.setOnClickListener(view1 -> {
            NavDirections actions = CourseFragmentDirections.actionCourseFragment2ToAddCourseFragment();
            Navigation.findNavController(view1).navigate(actions);
        });
        recyclerView = view.findViewById(R.id.rv_course);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        courseViewModel = new ViewModelProvider(getActivity()).get(CourseViewModel.class);
        courseViewModel.init(helper.getAccessToken());
        courseViewModel.getCourses();
        courseViewModel.getResultCourses().observe(getActivity(), showCourses);
    }

    List<Course.Courses> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Course> showCourses = new Observer<Course>() {
        @Override
        public void onChanged(Course course) {
            results = course.getCourses();
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            courseAdapter = new CourseAdapter(getActivity());
            courseAdapter.setCoursesList(results);
            recyclerView.setAdapter(courseAdapter);
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}