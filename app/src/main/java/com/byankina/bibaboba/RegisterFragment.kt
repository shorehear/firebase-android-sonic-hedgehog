package com.byankina.bibaboba

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var editTextRegisterEmail: EditText
    private lateinit var editTextRegisterMobile: EditText
    private lateinit var editTextRegisterPassword: EditText
    private lateinit var editTextRegisterConfirmPassword: EditText
    private lateinit var buttonByPhone: Button
    private lateinit var buttonByEmail: Button
    private lateinit var buttonRegister: Button
    private lateinit var buttonLoginBack: Button

    private lateinit var navController: NavController
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        initViews(view)
        setupListeners()
        setPhoneMode()

        return view
    }

    private fun initViews(view: View) {
        with(view) {
            editTextRegisterEmail = findViewById(R.id.editText_register_email)
            editTextRegisterMobile = findViewById(R.id.editText_register_mobile)
            editTextRegisterPassword = findViewById(R.id.editText_register_password)
            editTextRegisterConfirmPassword = findViewById(R.id.editText_register_confirm_password)
            buttonByPhone = findViewById(R.id.button_by_phone)
            buttonByEmail = findViewById(R.id.button_by_email)
            buttonRegister = findViewById(R.id.button_register)
            buttonLoginBack = findViewById(R.id.button_exit)
        }
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    private fun setupListeners() {
        buttonByPhone.setOnClickListener { setPhoneMode() }
        buttonByEmail.setOnClickListener { setEmailMode() }
        buttonRegister.setOnClickListener { validateAndRegister() }
        buttonLoginBack.setOnClickListener { navigateBackToLogin() }
    }

    private fun navigateBackToLogin() {
        navController.navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun setPhoneMode() {
        with(editTextRegisterEmail) {
            isEnabled = false
            visibility = View.GONE
        }
        with(editTextRegisterMobile) {
            isEnabled = true
            visibility = View.VISIBLE
            requestFocus()
        }
        buttonByPhone.setBackgroundColor(resources.getColor(R.color.green_200))
        buttonByEmail.setBackgroundColor(resources.getColor(R.color.gray_500))
    }

    private fun setEmailMode() {
        with(editTextRegisterMobile) {
            isEnabled = false
            visibility = View.GONE
        }
        with(editTextRegisterEmail) {
            isEnabled = true
            visibility = View.VISIBLE
            requestFocus()
        }
        buttonByEmail.setBackgroundColor(resources.getColor(R.color.green_200))
        buttonByPhone.setBackgroundColor(resources.getColor(R.color.gray_500))
    }

    private fun validateAndRegister() {
        val email = editTextRegisterEmail.text.toString().trim()
        val phone = editTextRegisterMobile.text.toString().trim()
        val password = editTextRegisterPassword.text.toString()
        val confirmPassword = editTextRegisterConfirmPassword.text.toString()

        when {
            editTextRegisterEmail.isEnabled && !email.contains("@") -> {
                showToast("Please enter a valid email address with '@'")
                return
            }
            !editTextRegisterEmail.isEnabled && !phone.startsWith("+") -> {
                showToast("Please enter a valid phone number with '+'")
                return
            }
            password.length < 8 -> {
                showToast("Password must be at least 8 characters long")
                return
            }
            password != confirmPassword -> {
                showToast("Passwords do not match")
                return
            }
        }

        if (editTextRegisterEmail.isEnabled) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Registration successful!")
                        navController.navigate(R.id.action_registerFragment_to_firstFragment)
                    } else {
                        showToast(task.exception?.message ?: "Registration failed")
                    }
                }
        } else {
            val sharedPreferences =
                requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val existingPhone = sharedPreferences.getString("phone", null)

            if (existingPhone == phone) {
                showToast("User already registered")
                return
            }

            with(sharedPreferences.edit()) {
                putString("phone", phone)
                putString("password", password)
                apply()
            }

            showToast("Registration successful!")
            navController.navigate(R.id.action_registerFragment_to_firstFragment)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}