package com.example.baseproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentCongratBinding;

public class CongratFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.example.baseproject.databinding.FragmentCongratBinding mBinding = FragmentCongratBinding.inflate(inflater, container, false);
        int score = CongratFragmentArgs.fromBundle(getArguments()).getScore();
        mBinding.tvScore.setText(getString(R.string.score,score));

        mBinding.btnRetest.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).popBackStack();
        });

        return mBinding.getRoot();
    }
}