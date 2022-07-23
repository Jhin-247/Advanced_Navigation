package com.example.baseproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.baseproject.databinding.FragmentFailedBinding;

public class FailedFragment extends Fragment {
    private FragmentFailedBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFailedBinding.inflate(inflater, container, false);
        mBinding.btnRetest.setOnClickListener(v -> Navigation.findNavController(mBinding.getRoot()).popBackStack());
        return mBinding.getRoot();
    }
}