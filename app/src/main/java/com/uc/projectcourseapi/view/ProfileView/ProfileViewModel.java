package com.uc.projectcourseapi.view.ProfileView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.uc.projectcourseapi.repositories.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository profileRepository;
    private static final String TAG = "ProfileViewModel";

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        Log.d(TAG, "token: " + token);
        profileRepository = ProfileRepository.getInstance(token);
    }

    public LiveData<String> logout() {
        profileRepository.resetInstances();
        return profileRepository.logout();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        profileRepository.resetInstances();
    }
}
