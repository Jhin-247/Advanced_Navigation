package com.example.baseproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.fragment.QuizFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = QuizFragment.class.getSimpleName();
    private NavController mNavController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        NavHostFragment mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (mNavHostFragment != null) {
            mNavController = mNavHostFragment.getNavController();
            NavigationUI.setupActionBarWithNavController(this, mNavController);
        }


        /* TODO Log behavior => Cần test tiếp
         Vấn đề: khi đặt menu từ fragment quiz, nếu nhận fragment/action id trước:
         Behavior mong muốn:
         - Khi không có "secondary": sẽ pop hết fragment và navigate đến destinationid
         - Khi có "secondary": hoạt động như hàm navigate bình thường
         Thực tế:
         - Khi không có "secondary": pop hết fragment và navigate đến fragment gọi tới menu item (trong trường hơp này là quiz)
            => hàm đang trả về false do không phải destination mong muốn:
            TODO navController.currentDestination?.matchDestination(item.itemId) == true
            => đang quan sát thay đổi của back stack (trong code đang là ArrayDeque :v)
         - Khi có "secondary": hoạt động như hàm navigate bình thường
         */
        HandlerThread thread = new HandlerThread("1");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(() -> {
            int num = mNavController.getBackQueue().size();
            while (true) {
                if (mNavController.getBackQueue().size() != num) {
                    num = mNavController.getBackQueue().size();
                    Log.i(TAG, "onBackQueueSizeChange: " + num);
                    for (NavBackStackEntry navBackStackEntry : mNavController.getBackQueue()) {
                        Log.i(TAG, "onBackQueueSizeChange: " + navBackStackEntry.getDestination().getDisplayName());
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        // 2 vì luôn có 1 nav_graph + fragment đầu tiên => activity duy nhất => back thoát app luôn
        // dungf navigateup sẽ không thể thoát app
        if (mNavController.getBackQueue().size() == 2) {
            super.onBackPressed();
        } else {
            mNavController.popBackStack();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return mNavController.navigateUp() || super.onSupportNavigateUp();
    }
}
