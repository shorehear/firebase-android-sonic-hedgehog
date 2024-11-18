package com.byankina.bibaboba;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainFragment extends Fragment {

    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    private NavController navController;

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_AUTO_LOGIN = "auto_login";

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(this::checkUserPreferences, 2000);

        Button buttonLogin = view.findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v -> startLoginActivity());

        TextView textViewRegisterLink = view.findViewById(R.id.textView_register_link);
        textViewRegisterLink.setOnClickListener(v -> startRegisterActivity());

        return view;
    }

    private void checkUserPreferences() {
        progressBar.setVisibility(View.GONE);

        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String phone = sharedPreferences.getString(KEY_PHONE, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);
        boolean autoLogin = sharedPreferences.getBoolean(KEY_AUTO_LOGIN, false);

        if (email != null && password != null && autoLogin) {
            // Navigate to ContentActivity if auto-login is enabled
            navController.navigate(R.id.action_MainFragment_to_firstFragment);
        } else if ((email != null || phone != null) && password != null) {
            startLoginActivity();
        } else {
            startRegisterActivity();
        }
    }

    private void startLoginActivity() {
        navController.navigate(R.id.LoginFragment);
    }

    private void startRegisterActivity() {
        navController.navigate(R.id.RegisterFragment);
    }
}
