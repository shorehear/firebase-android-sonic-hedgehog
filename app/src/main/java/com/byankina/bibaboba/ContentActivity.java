package com.byankina.bibaboba;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.ui.NavigationUI;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            NavigationUI.setupWithNavController(bottomNavigationView, navController);

            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                if (destination.getId() == R.id.LoginFragment ||
                        destination.getId() == R.id.RegisterFragment ||
                        destination.getId() == R.id.MainFragment) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            });
        } else {
            throw new IllegalStateException("NavHostFragment is null. Check your layout XML.");
        }
    }
}
