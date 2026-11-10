
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button emergencyButton;
    Button contactButton;
    Button locationButton;
    Button servicesButton;
    Button infoButton;
    Button logoutButton;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        emergencyButton = findViewById(R.id.emergencyButton);
        contactButton = findViewById(R.id.contactButton);
        locationButton = findViewById(R.id.locationButton);
        servicesButton = findViewById(R.id.servicesButton);
        infoButton = findViewById(R.id.infoButton);
        logoutButton = findViewById(R.id.logoutButton);

        emergencyButton.setOnClickListener(v -> {
            openPage("EMERGENCY CALL");
        });

        contactButton.setOnClickListener(v -> {
            openPage("CONTACTS");
        });

        locationButton.setOnClickListener(v -> {
            openPage("LOCATION");
        });

        servicesButton.setOnClickListener(v -> {
            openPage("SERVICES");
        });

        infoButton.setOnClickListener(v -> {
            openPage("INFO");
        });

        logoutButton.setOnClickListener(v -> {
            logoutUser();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() == null) {
            openLoginPage();
        }
    }

    private void openPage(String title) {
        Intent intent = new Intent(MainActivity.this, PageActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    private void logoutUser() {
        firebaseAuth.signOut();

        Toast.makeText(this, "Logged out successfully!",
                Toast.LENGTH_SHORT).show();

        openLoginPage();
    }

    private void openLoginPage() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
