package com.byankina.bibaboba;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressBar progressBar;
    private NavController navController;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = view.findViewById(R.id.editText_login_email);
        editTextPassword = view.findViewById(R.id.editText_login_pwd);
        Button buttonLogin = view.findViewById(R.id.button_login);
        progressBar = view.findViewById(R.id.progressBar);
        TextView textViewRegisterLink = view.findViewById(R.id.textView_register_link);
        TextView textViewForgotPasswordLink = view.findViewById(R.id.textView_forgot_password_link);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        buttonLogin.setOnClickListener(v -> loginUser());

        textViewRegisterLink.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        });

        textViewForgotPasswordLink.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void loginUser() {
        String emailOrPhone = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (emailOrPhone.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter email/phone and password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE);
        String savedPhone = sharedPreferences.getString("phone", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (savedPhone != null && savedPhone.equals(emailOrPhone)) {
            if (savedPassword != null && savedPassword.equals(password)) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show();

                navController.navigate(R.id.action_loginFragment_to_contentFragment);
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailOrPhone, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.action_loginFragment_to_contentFragment);
                        } else {
                            Toast.makeText(requireContext(), "Authentication failed: " +
                                            (task.getException() != null ? task.getException().getMessage() : "Unknown error"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

}
