package com.example.baseproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentQuizBinding;

@SuppressWarnings("deprecation")
public class QuizFragment extends Fragment {
    private static final String TAG = QuizFragment.class.getSimpleName();
    private FragmentQuizBinding mBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentQuizBinding.inflate(inflater, container, false);
        mBinding.btnTrue.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).navigate(QuizFragmentDirections.actionQuizFragmentToCongratFragment2().setScore(100));
        });


        mBinding.btnFalse.setOnClickListener(v -> {
            Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_quizFragment_to_failedFragment);

//            NavOptions.Builder builder = new NavOptions.Builder()
//                    .setLaunchSingleTop(true)
//                    .setRestoreState(true);

//            if (item.getOrder() == 0 && Menu.CATEGORY_SECONDARY == 0) {
//            builder.setPopUpTo(NavHostFragment.findNavController(this)
//                            .getGraph()
//                            .getStartDestinationId(),
//                    false,
//                    true);
//            }

//            NavOptions navOptions = builder.build();
//            NavHostFragment.findNavController(this).navigate(R.id.onBoardingFragment, null, navOptions);

        });


        setHasOptionsMenu(true);
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_quiz, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected: " + Navigation.findNavController(mBinding.getRoot()).getBackQueue().size());
        for (NavBackStackEntry navBackStackEntry : Navigation.findNavController(mBinding.getRoot()).getBackQueue()) {
            Log.i(TAG, "onOptionsItemSelected: " + navBackStackEntry.getDestination().getDisplayName());
        }

        // Tạm thời dùng navigate do chưa rõ cơ chế onNavDestinationSelected
        // Khi dùng onNavDestinationSelected với actionId => không áp
        if (item.getItemId() == R.id.action_quizFragment_to_onBoardingFragment) {
            Navigation.findNavController(mBinding.getRoot()).navigate(item.getItemId());
            return true;
        }

        return NavigationUI.onNavDestinationSelected(item, NavHostFragment.findNavController(this)) || super.onOptionsItemSelected(item);
    }
}