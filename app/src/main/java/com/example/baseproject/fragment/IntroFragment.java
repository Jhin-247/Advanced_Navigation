package com.example.baseproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentIntroBinding;

public class IntroFragment extends Fragment {
    private FragmentIntroBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentIntroBinding.inflate(inflater, container, false);

        mBinding.btnStart.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_introFragment_to_quizFragment);
        });

        return mBinding.getRoot();
    }
}