package com.example.valerastores;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.valerastores.Model.My_Models;
import com.example.valerastores.databinding.ActivitySignupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the view binding
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize FirebaseAuth and Firestore
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Initialize ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Your Account");
        progressDialog.setMessage("Please wait...");

        // Create account button click listener
        binding.createaccountbtn.setOnClickListener(v -> {
            String name = binding.username.getText().toString();
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();

            // Validate inputs
            if (name.isEmpty()) {
                binding.username.setError("Enter your Name");
            } else if (email.isEmpty()) {
                binding.email.setError("Enter your Email");
            } else if (password.isEmpty()) {
                binding.password.setError("Enter your Password");
            } else {
                // Show ProgressDialog while processing
                progressDialog.show();

                // Create user with Firebase Authentication
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Create a new user model object
                                My_Models userModel = new My_Models(name, email, password);

                                // Get the Firebase user ID and use it as the document ID
                                String userId = task.getResult().getUser().getUid();

                                // Save user data to Firestore using the user ID as the document ID
                                firestore.collection("users").document(userId).set(userModel)
                                        .addOnCompleteListener(task1 -> {
                                            progressDialog.dismiss();
                                            if (task1.isSuccessful()) {
                                                // Account creation and Firestore save successful
                                                Toast.makeText(SignupActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                                                // Redirect to LoginActivity
                                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                                finish();  // Close SignupActivity
                                            } else {
                                                // Firestore save failed
                                                Toast.makeText(SignupActivity.this, "Error saving user data: " + task1.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                // Firebase Authentication failed
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Error: " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Login link click listener
        binding.logintxt.setOnClickListener(v -> {
            // Redirect to LoginActivity
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish(); // Close SignupActivity
        });

        // Handle system bars for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
