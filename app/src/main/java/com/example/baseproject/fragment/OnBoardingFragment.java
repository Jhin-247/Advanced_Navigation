package com.example.baseproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentOnboardingBinding;

public class OnBoardingFragment extends Fragment {
    private FragmentOnboardingBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentOnboardingBinding.inflate(inflater, container, false);

        mBinding.btnNext.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_onBoardingFragment_to_introFragment);
        });

        return mBinding.getRoot();
    }
}